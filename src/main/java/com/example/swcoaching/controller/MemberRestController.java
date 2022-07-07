package com.example.swcoaching.controller;

import com.example.swcoaching.member.MemberService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping
public class MemberRestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MemberService memberService;

}
