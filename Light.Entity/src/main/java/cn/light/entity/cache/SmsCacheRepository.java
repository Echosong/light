package cn.light.entity.cache;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsCacheRepository extends JpaRepository<SmsCache, String> {
}
