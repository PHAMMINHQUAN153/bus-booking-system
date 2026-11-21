package com.minhquan.busbooking.controller;

import com.minhquan.busbooking.entity.Route;
import com.minhquan.busbooking.repository.RouteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteRepository routeRepository;

    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @GetMapping
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Route getById(@PathVariable Long id) {
        return findOrThrow(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Route create(@Valid @RequestBody Route route) {
        route.setId(null); // bỏ id client gửi lên, để CSDL tự tăng
        return routeRepository.save(route);
    }

    @PutMapping("/{id}")
    public Route update(@PathVariable Long id, @Valid @RequestBody Route data) {
        Route route = findOrThrow(id);
        route.setOrigin(data.getOrigin());
        route.setDestination(data.getDestination());
        route.setDistanceKm(data.getDistanceKm());
        route.setDurationMinutes(data.getDurationMinutes());
        return routeRepository.save(route);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        findOrThrow(id);
        routeRepository.deleteById(id);
    }

    private Route findOrThrow(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Không tìm thấy tuyến có id = " + id));
    }
}