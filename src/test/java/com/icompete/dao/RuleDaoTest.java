/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icompete.dao;

import com.icompete.entity.Event;
import com.icompete.entity.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class to test RuleDao CRUD operations
 * @author Xhulio
 */
@ContextConfiguration(locations = "file:src/main/resources/spring-config.xml")
public class RuleDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private RuleDao ruleDao;
    /**
     * Tests rule entity creation and retrieval
     */
    @Test
    public void testCreateAndFind() {
        
       Rule rule = new Rule();
       rule.setText("First rule");
       
       ruleDao.create(rule);
       
       Rule savedRule = ruleDao.findById(rule.getId());
       Assert.assertNotNull(savedRule);
       Assert.assertEquals(savedRule.getText(), "First rule");
       
       /**
        * We are testing for null value because we don't know the order of execution of the test
        * so we cant check for list size because more than one entity can exists at this point
        */
       Assert.assertNotNull(ruleDao.findAll());
        
    }

    /**
     * Test rule entity update
     */
    @Test
    public void testUpdate() {
       Rule rule = new Rule();
       rule.setText("Second rule");
       
       ruleDao.create(rule);
       
       rule.setText("Second");
       
       ruleDao.update(rule);
       
       Rule updatedRule = ruleDao.findById(rule.getId());
       
       Assert.assertEquals(updatedRule.getText(), "Second");
    }

    /**
     * Test rule entity deletion
     */
    @Test
    public void testDelete() {
        Rule rule = new Rule();
        rule.setText("Third rule");
        
        ruleDao.create(rule);
        
        Assert.assertNotNull(ruleDao.findById(rule.getId()));
        
        ruleDao.delete(rule);
        
        Assert.assertNull(ruleDao.findById(rule.getId()));
    }
    
    /**
     * Test creation of rule with a unsaved Event entity
     */
    @Test
    public void testEventInsertion(){
        Rule rule = new Rule();
        rule.setText("Forth rule");
        
        Event event = new Event();
        event.setName("Swimming");
        event.setAddress("Adress");
        event.setCapacity(30);
        rule.setEvent(event);
        
        ruleDao.create(rule);
        
        Rule savedRule = ruleDao.findById(rule.getId());
        Assert.assertNotNull(savedRule.getEvent());
    }
}
