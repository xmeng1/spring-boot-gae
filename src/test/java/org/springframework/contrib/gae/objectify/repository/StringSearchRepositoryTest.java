package org.springframework.contrib.gae.objectify.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.contrib.gae.objectify.TestStringEntity;
import org.springframework.contrib.gae.search.Operator;
import org.springframework.contrib.gae.search.query.Query;
import org.springframework.contrib.gae.search.query.Result;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ConstantConditions")
public class StringSearchRepositoryTest extends AbstractStringRepositoryTest {

    @Autowired
    private SearchRepository<TestStringEntity, String> repository;

    @Test
    public void save_willIndexInSearchService() {
        TestStringEntity target = new TestStringEntity("id2").setName("name2");
        repository.save(
                new TestStringEntity("id1").setName("name1"),
                target,
                new TestStringEntity("id3").setName("name3")
        );

        assertThat(searchByName("name2"))
                .containsExactly(target);
    }

    @Ignore("https://github.com/3wks/spring-boot-gae/issues/4")
    @Test
    public void save_willUpdateIndex_whenSavedTwice() {
        TestStringEntity target = new TestStringEntity("id2").setName("name2");
        repository.save(target);

        assertThat(searchByName("name2"))
                .containsExactly(target);

        target.setName("name2 updated");
        repository.save(target);

        assertThat(searchByName("name2 updated"))
                .containsExactly(target);
        assertThat(searchByName("name2"))
                .isEmpty();
    }

    @Test
    public void delete_willRemoveEntryFromSearchService() {
        TestStringEntity target = new TestStringEntity("id3").setName("target");

        repository.save(
                new TestStringEntity("id1").setName("name1"),
                new TestStringEntity("id2").setName("name2"),
                target
        );

        assertThat(searchByName(target.getName()))
                .containsExactly(target);

        repository.delete(target);

        assertThat(searchByName(target.getName()))
                .isEmpty();
    }

    private Result<TestStringEntity> searchByName(String name) {
        Query<TestStringEntity> query = repository.search()
                .filter("name", Operator.EQ, name)
                .build();
        return repository.execute(query);
    }

}
