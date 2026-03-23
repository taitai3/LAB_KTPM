package iuh.fit.user.controller;

import iuh.fit.user.model.UserPartition;
import iuh.fit.user.repository.UserTable01Repository;
import iuh.fit.user.repository.UserTable02Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserTable01Repository table01Repo;
    private final UserTable02Repository table02Repo;

    // Lấy user từ partition 01 (id lẻ)
    @GetMapping("/partition/01")
    public List<?> getPartition01() {
        return table01Repo.findAll();
    }

    // Lấy user từ partition 02 (id chẵn)
    @GetMapping("/partition/02")
    public List<?> getPartition02() {
        return table02Repo.findAll();
    }

    // Lấy tất cả - merge 2 partition
    @GetMapping
    public List<UserPartition> getAll() {
        List<UserPartition> all = new java.util.ArrayList<>();
        all.addAll(table01Repo.findAll());
        all.addAll(table02Repo.findAll());
        return all;
    }
}
