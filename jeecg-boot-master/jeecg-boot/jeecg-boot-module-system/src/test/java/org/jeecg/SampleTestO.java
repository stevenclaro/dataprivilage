package org.jeecg;

import org.jeecg.modules.demo.mock.MockController;

import org.jeecg.modules.demo.test.entity.JeecgDemo;
import org.jeecg.modules.demo.test.mapper.JeecgDemoMapper;
import org.jeecg.modules.demo.test.service.IJeecgDemoService;
import org.jeecg.modules.system.service.ISysDataLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.management.relation.RelationService;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleTestO {

	@Resource
	private JeecgDemoMapper jeecgDemoMapper;
	@Resource
	private IJeecgDemoService jeecgDemoService;
	@Resource
	private ISysDataLogService sysDataLogService;
	@Resource
	private MockController mock;
	/*@Autowired
	private RelationServiceImpl relationService;*/

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
	/*	Set<String> stringList= relationService.getAllsonID("sjk");
		stringList.forEach(r->{
			System.out.println(r.toString()+"\r\n");
		});
*/
	}

}
