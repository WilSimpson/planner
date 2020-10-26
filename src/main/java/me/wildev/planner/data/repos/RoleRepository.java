package me.wildev.planner.data.repos;

import me.wildev.planner.data.ERole;
import me.wildev.planner.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByRole(ERole role);
}
