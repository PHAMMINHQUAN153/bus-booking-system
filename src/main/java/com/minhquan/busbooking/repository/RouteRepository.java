package com.minhquan.busbooking.repository;

import com.minhquan.busbooking.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}