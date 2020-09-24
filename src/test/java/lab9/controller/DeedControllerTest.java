package lab9.controller;

import lab9.model.Deed;
import lab9.repository.DeedRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeedControllerTest {

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    public DeedRepository repository;

    @Before
    public void CreateDb()
    {

    }

    @After
    public void resetDb() {
        repository.deleteAll();
        repository.flush();
    }

    @Test
    public void Create() {

        //Deed deed = new Deed("Test Controller", "TESTING");
        Deed deed = createTestDeed("Test Controller", "TESTING");
        Integer id = deed.getId();
        ResponseEntity<Deed> response = restTemplate.postForEntity("/deeds", deed, Deed.class);

        Deed find_deed = repository.getOne(id);
        assertThat(find_deed.getId(), notNullValue());
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    public void GetDeedByID() {

        Integer id = createTestDeed("Eat chocolate","DONE").getId();

        Deed deed = restTemplate.getForObject("/deeds/{id}", Deed.class, id);
        assertThat(deed.getName(), is("Eat chocolate"));
        assertThat(deed.getStatus(), is("DONE"));
    }

    @Test
    public void Update() {

        Integer id = createTestDeed("Watch a movie","PENDING").getId();
        Deed deed = new Deed("Clean the room","DELAYED");
        HttpEntity<Deed> entity = new HttpEntity<Deed>(deed);

        ResponseEntity<Deed> response = restTemplate.exchange("/deeds/{id}", HttpMethod.PUT, entity, Deed.class, id);
        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));

        Deed find_deed = repository.getOne(id);
        assertThat(find_deed.getId(), notNullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));

    }

    @Test
    public void Delete() {

        Integer id = createTestDeed("Take a shower","DONE").getId();
        restTemplate.delete("/deeds/{id}", id);
    }

    @Test
    public void GetAllDeeds() {
        createTestDeed("Deed1","status1");
        createTestDeed("Deed2","status2");
        ResponseEntity<List<Deed>> response = restTemplate.exchange("/deeds", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Deed>>() {
                });
        List<Deed> deeds = response.getBody();
        assertThat(deeds, hasSize(2));
        assertThat(deeds.get(1).getName(), is("Deed2")); //????
    }

    private Deed createTestDeed(String name, String status) {
        Deed emp = new Deed(name,status);
        return repository.saveAndFlush(emp);
    }
}