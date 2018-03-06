package com.tangcheng.editor.rte.ck.editor.controller;

import com.tangcheng.editor.rte.ck.editor.domain.BlogCKEditor;
import com.tangcheng.editor.rte.ck.editor.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

/**
 * @author tangcheng
 *         2017/11/09
 */
@Slf4j
@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("blog")
    public String list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<BlogCKEditor> page = blogRepository.findAll(pageable);
        model.addAttribute("blogs", page.getContent());
        return "admin/index";
    }

    @GetMapping("blog/{id}/detail")
    public String get(@PathVariable("id") Long blogId, Model model) {
        BlogCKEditor blog = blogRepository.findOne(blogId);
        model.addAttribute("blog", blog);
        return "admin/detail";
    }

    @GetMapping("blog/{id}/edit")
    public String edit(@PathVariable("id") Long blogId, Model model) {
        BlogCKEditor blog = blogRepository.findOne(blogId);
        model.addAttribute("blog", blog);
        return "admin/edit";
    }

    @GetMapping("blog/{id}/delete")
    public String delete(@PathVariable("id") Long blogId) {
        blogRepository.delete(blogId);
        return "redirect:/blog";
    }

    @PostMapping("blog")
    public String add(BlogCKEditor blogReq) {
        Date now = new Date();
        blogReq.setCreateTime(now);
        blogReq.setUpdateTime(now);
        blogRepository.save(blogReq);
        return "redirect:/blog";
    }

}
