package com.tangcheng.editor.rte.ck.editor.repository;

import com.tangcheng.editor.rte.ck.editor.domain.BlogCKEditor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tangcheng
 * 2017/11/10
 */
public interface BlogRepository extends JpaRepository<BlogCKEditor, Long> {
}
