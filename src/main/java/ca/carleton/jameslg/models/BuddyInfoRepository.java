package ca.carleton.jameslg.models;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "buddy", path = "buddy")
public interface BuddyInfoRepository extends PagingAndSortingRepository<BuddyInfo, Integer> {
    List<BuddyInfo> findByName(@Param("name") String name);

    BuddyInfo findById(@Param("id") int id);
}
