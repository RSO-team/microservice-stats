package si.fri.rsoteam.services.beans;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rsoteam.entities.StatsEntity;
import si.fri.rsoteam.lib.dtos.StatsDto;
import si.fri.rsoteam.services.mappers.StatsMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@RequestScoped
public class StatsBean {
    private Logger log = LogManager.getLogger(StatsBean.class.getName());

    @Inject
    private EntityManager em;

    @Timed
    public StatsDto getStats(Integer id){
        return StatsMapper.entityToDto(em.find(StatsEntity.class,id));
    }

    @Timed
    public List<StatsDto> getAllStats(){
        return em.createNamedQuery("Stats.getAll",StatsEntity.class)
                .getResultList()
                .stream()
                .map(StatsMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public StatsDto createStats(StatsDto statsDto) {
        StatsEntity statsEntity = StatsMapper.dtoToEntity(statsDto);
        this.beginTx();
        em.persist(statsEntity);
        this.commitTx();

        return StatsMapper.entityToDto(statsEntity);
    }

    public StatsDto updateStats(StatsDto statsDto, Integer id) {
        this.beginTx();

        StatsEntity statsEntity = em.find(StatsEntity.class, id);
        statsEntity.setCategory(statsDto.category);
        statsEntity.setValue(statsDto.value);
        em.persist(statsEntity);
        this.commitTx();

        return StatsMapper.entityToDto(statsEntity);
    }

    public void deleteStats(Integer id) {
        StatsEntity statsEntity = em.find(StatsEntity.class, id);
        if (statsEntity != null) {
            this.beginTx();
            em.remove(statsEntity);
            this.commitTx();
        }
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
