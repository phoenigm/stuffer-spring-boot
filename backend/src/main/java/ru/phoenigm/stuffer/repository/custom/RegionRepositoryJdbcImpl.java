package ru.phoenigm.stuffer.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.phoenigm.stuffer.domain.Region;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class RegionRepositoryJdbcImpl implements RegionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Region> findAllByNameIgnoreCaseContaining(String name) {
        String query = "SELECT * FROM region WHERE name ILIKE ?";
        return jdbcTemplate.queryForList(query, Region.class, "%" + name + "%");
    }

    private RowMapper<Region> rowMapper() {
        return (resultSet, i) -> Region.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }

    @Override
    public Region findById(Long id) {
        String query = "SELECT * FROM region WHERE id = ?;";
        return jdbcTemplate.queryForObject(query, rowMapper(), id);
    }

    @Override
    public List<Region> findAll() {
        String query = "SELECT * FROM region;";
        return jdbcTemplate.queryForList(query, Region.class);
    }

    @Override
    public Region save(Region model) {
        String query = "INSERT INTO region(name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(query, new String[]{"id"});
                    ps.setString(1, model.getName());
                    return ps;
                },
                keyHolder);
        model.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return model;
    }

    @Override
    public void updateById(Long id, Region model) {
        String query = "UPDATE region SET (name) = (?) WHERE id = ?";
        jdbcTemplate.update(query, model.getName(), model.getId());

    }

    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM region WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
