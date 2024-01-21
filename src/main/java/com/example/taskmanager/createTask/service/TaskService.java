package com.example.taskmanager.createTask.service;

import com.example.taskmanager.application.component.CurrentUser;
import com.example.taskmanager.application.domain.*;
import com.example.taskmanager.application.exception.ProcessException;
import com.example.taskmanager.createTask.dto.request.TaskRequestDTO;
import com.example.taskmanager.createTask.dto.response.TaskPreviewResponseDTO;
import com.example.taskmanager.createTask.repository.*;
import com.example.taskmanager.task.dto.response.TaskResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final Logger LOG = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final String imagesPath;

    public TaskService(TaskRepository taskRepository, TypeRepository typeRepository,
                           CategoryRepository categoryRepository, AttributeRepository attributeRepository,
                           UserRepository userRepository, CurrentUser currentUser, @Value("${imagesPath}") String imagesPath) {
        this.taskRepository = taskRepository;
        this.typeRepository = typeRepository;
        this.categoryRepository = categoryRepository;
        this.attributeRepository = attributeRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.imagesPath = imagesPath;
    }

    public Long saveOrUpdateItem(TaskRequestDTO itemRequestDTO, MultipartFile file) throws ProcessException, IOException {

        User user = userRepository.findById(currentUser.getId()).orElseThrow(userNotFound());
        Type type = typeRepository.findById(itemRequestDTO.getSelectedType()).orElseThrow(typeNotFound());

        List<Category> categories = categoryRepository.findAllById(itemRequestDTO.getSelectedCategories());
        List<Attribute> attributes = attributeRepository.findAllById(itemRequestDTO.getSelectedAttributes());

        Task item = createItemIfNew(itemRequestDTO);
        item.setName(itemRequestDTO.getName());
        item.setDateTimeFrom(itemRequestDTO.getDateTimeFrom());
        item.setDateTimeTo(itemRequestDTO.getDateTimeTo());
        item.setType(type);
        item.setCategories(categories);
        item.setDescription(itemRequestDTO.getDescription());
        item.setAmount(itemRequestDTO.getAmount());
        item.setComment(itemRequestDTO.getComment());
        item.setStatus(itemRequestDTO.getStatus());
        item.setCategories(categories);
        item.setAttributes(attributes);
        item.setCreator(user);
        item.setImage(itemRequestDTO.getImage());

        boolean fakeIdCreated = fakeId(item);
        if(fakeIdCreated) {
            taskRepository.save(item);
            storeItemImage(itemRequestDTO, file, item);
            taskRepository.save(item);
        }
        else {
            throw new ProcessException("Create item failed");
        }

        return item.getId();
    }

    private void storeItemImage(TaskRequestDTO itemRequestDTO, MultipartFile file, Task item) throws IOException {
        if (file != null) {
            String image = itemRequestDTO.getImage();
            item.setImage(image);
            saveFile(file, item);
        }
    }

    private boolean fakeId(Task item) {
        AtomicBoolean fakeIdNull = new AtomicBoolean(false);
        try {
            if (item.getFakeId() == null) {
                fakeIdNull.set(true);
                item.setFakeId(currentUser.inc());
            }
            return true;
        } catch (Exception e) {
            if (fakeIdNull.get()) {
                currentUser.dec();
            }
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    private void saveFile(MultipartFile file, Task item) throws IOException {
        File dir = new File(imagesPath + "appaItem-" + item.getId() + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir.getAbsolutePath() + "/" + file.getOriginalFilename());
        file.transferTo(newFile);
    }

    private Task createItemIfNew(TaskRequestDTO itemRequestDTO) throws ProcessException {
        Task item;
        if (itemRequestDTO.getId() == null) {
            item = new Task();
        } else {
            item = taskRepository.findById(itemRequestDTO.getId()).orElseThrow(() -> new RuntimeException("Item not exists"));
            if (item == null) {
                throw new ProcessException("Selected item does not exist");
            }
        }

        return item;
    }

    public List<TaskPreviewResponseDTO> getItems() {
        Sort sort = Sort.by("creationDate").descending();
        User user = userRepository.findById(currentUser.getId()).orElseThrow(userNotFound());
        List<Task> items = taskRepository.findAllLoggedUser(user, sort);
        List<TaskPreviewResponseDTO> itemPreviewResponseDTOs = items.stream().map(this::itemPreviewResponseDTO)
                .collect(Collectors.toList());
        LOG.info("getAll - number of appa items: " + itemPreviewResponseDTOs.size());

        return itemPreviewResponseDTOs;
    }

    private TaskPreviewResponseDTO itemPreviewResponseDTO(Task item) {
        return new TaskPreviewResponseDTO(item.getId(),
                item.getFakeId(), item.getName(), item.getDateTimeFrom(), item.getDateTimeTo());
    }

//    public TaskResponseDTO getItem(Long id) {
//
//        User user = userRepository.findById(currentUser.getId()).orElseThrow(userNotFound());
//        Task item = taskRepository.findByCreatorAndFakeId(user, id);
//
//        List<Category> categories = item.getCategories();
//        List<Long> categoriesIds = categories.stream().map(category -> category.getId()).collect(Collectors.toList());
//        List<Attribute> attributes = item.getAttributes();
//        List<Long> attributesIds = attributes.stream().map(attribute -> attribute.getId()).collect(Collectors.toList());
//
//        TaskResponseDTO itemResponseDTO = new TaskResponseDTO(item.getId(), item.getName(),
//                item.getAmount(), item.getComment(), item.getDescription(), item.getDateTimeFrom(),
//                item.getDateTimeTo(), attributesIds, categoriesIds, item.getType().getId(),
//                item.getStatus(), item.getImage());
//
//        return itemResponseDTO;
//
//    }

    private Supplier<? extends RuntimeException> userNotFound() {
        return () -> new RuntimeException("User not found");
    }

    private Supplier<? extends RuntimeException> typeNotFound() {
        return () -> new RuntimeException("Type not found");
    }
}
