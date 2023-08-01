package com.testsystem.back_java.services.impl;

import com.testsystem.back_java.dto.TestDto;
import com.testsystem.back_java.exception.UserNotFoundException;
import com.testsystem.back_java.models.Subcategory;
import com.testsystem.back_java.models.Test;
import com.testsystem.back_java.models.User;
import com.testsystem.back_java.repo.SubcategoryRepository;
import com.testsystem.back_java.repo.TestRepository;
import com.testsystem.back_java.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements ITestService{
    private final TestRepository testRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository, SubcategoryRepository subcategoryRepository, UserRepository userRepository) {
        this.testRepository = testRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Test> findAllTest() {
        List<Test> tests = new ArrayList<>();
        testRepository.findAll().forEach(tests::add);
        return null;
    }

    @Override
    public Test getTestById(long id) {
        Optional<Test> findTest = testRepository.findById(id);
        if (findTest.isPresent()) return findTest.get();
        return null;
    }

    @Override
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public void updateTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public void deleteTest(long id) {
        testRepository.deleteById(id);

    }

    @Override
    public Test convertTestDtoToEntity(TestDto testDto) throws Exception {
       Test test = new Test();
       test.setName(testDto.getName());
       test.setDescription(testDto.getDescription());
       test.setIsPublic(testDto.getIsPublic());
       Optional<Subcategory> subcategory = subcategoryRepository.findById(testDto.getSubcategoryId());
       if (subcategory.isPresent()){
           test.setSubcategory(subcategory.get());
       }else {
           throw new Exception("Підкатегорію не знайдено");
       }
        User user = userRepository.findUserById(testDto.getOwner().getId());
       if (user==null)throw new UserNotFoundException();
        test.setOwner(user);
        return test;
    }
}
