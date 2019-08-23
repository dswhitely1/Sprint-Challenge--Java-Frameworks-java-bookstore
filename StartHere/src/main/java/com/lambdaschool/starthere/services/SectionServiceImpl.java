//package com.lambdaschool.starthere.services;
//
//import com.lambdaschool.starthere.models.Section;
//import com.lambdaschool.starthere.repository.SectionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service(value="sectionService")
//public class SectionServiceImpl implements SectionService
//{
//    @Autowired
//    SectionRepository sectionRepo;
//
//    @Override
//    public Section save(Section section)
//    {
//        Section newSection = new Section();
//        newSection.setName(newSection.getName());
//
//        return sectionRepo.save(newSection);
//    }
//}