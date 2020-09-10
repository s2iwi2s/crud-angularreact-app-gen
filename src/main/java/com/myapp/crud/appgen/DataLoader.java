package com.myapp.crud.appgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.javafaker.Faker;
import com.myapp.crud.appgen.codeGroups.CodeGroups;
import com.myapp.crud.appgen.codeGroups.CodeGroupsDAO;
import com.myapp.crud.appgen.home.Home;
import com.myapp.crud.appgen.home.HomeDAO;
import com.myapp.crud.appgen.myCase.MyCase;
import com.myapp.crud.appgen.myCase.MyCaseDAO;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
//	@Autowired
//	AuthorHibernateDAOSupport authorHibernateDAOSupport;
//	
	@Autowired
	private HomeDAO homeDAO;

	@Autowired
	private CodeGroupsDAO codeGroupsDAO;

	@Autowired
	private MyCaseDAO myCasesDAO;

//	@Autowired
//	BookDAO bookDAO;

	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		//setHome();
		load();
	}

	private void setHome() {
		List<Home> homes = new ArrayList<Home>();
		homes.add(new Home("/my-case-list", "My Cases"));
		homes.add(new Home("/part-item-list", "Part Items"));
		homes.add(new Home("/serial-item-list", "Serial Items"));
//		homes.add(new Home("/author-list", "Authors"));
//		homes.add(new Home("/books-list", "Books"));

		homeDAO.saveAll(homes);
	}

	private void load() {
		System.out.println("DataLoader.load - START");
		Faker faker = new Faker();

//		final String[] status = { "New", "Dispatched", "Ack", "Closed" };
//		List<CodeGroups> statuslistAdd = addCodeGroup(status, "STATUS");
//		codeGroupsDAO.saveAll(statuslistAdd);

//		final String[] statusCode = { "Open", "Open-Dispatched", "Awaiting Customer", "Closed" };
//		List<CodeGroups> statusCodelist = addCodeGroup(statusCode, "STATUS_CODE");
//		codeGroupsDAO.saveAll(statusCodelist);

//		String[] caseType1 = new String[20];
//		for (int i = 0; i < caseType1.length; i++) {
//			caseType1[i] = faker.lorem().word();
//		}
//		List<CodeGroups> caseType1list = addCodeGroup(caseType1, "CASE_TYPE_1");
//		codeGroupsDAO.saveAll(caseType1list);

//		String[] caseType2 = new String[20];
//		for (int i = 0; i < caseType2.length; i++) {
//			caseType2[i] = faker.lorem().word();
//		}
//		List<CodeGroups> caseType2list = addCodeGroup(caseType2, "CASE_TYPE_2");
//		codeGroupsDAO.saveAll(caseType2list);

//		String[] caseType3 = new String[20];
//		for (int i = 0; i < caseType3.length; i++) {
//			caseType3[i] = faker.lorem().word();
//		}
//		List<CodeGroups> caseType3list = addCodeGroup(caseType3, "CASE_TYPE_3");
//		codeGroupsDAO.saveAll(caseType3list);

		
		final List<CodeGroups> statuslist = codeGroupsDAO.findByCode("STATUS");
		List<CodeGroups> caseType1list = codeGroupsDAO.findByCode("CASE_TYPE_1");
		List<CodeGroups> caseType2list = codeGroupsDAO.findByCode("CASE_TYPE_2");
		List<CodeGroups> caseType3list = codeGroupsDAO.findByCode("CASE_TYPE_3");
		List<CodeGroups> statusCodelist = codeGroupsDAO.findByCode("STATUS_CODE");
		
		Random random = new Random();
		List<MyCase> myCasesList = new ArrayList<MyCase>();
		IntStream.range(1, 200).forEach(i -> {
			int statusir = random.nextInt(statuslist.size());
			int caseType1ir = random.nextInt(caseType1list.size());
			int caseType2ir = random.nextInt(caseType2list.size());
			int caseType3ir = random.nextInt(caseType3list.size());
			int statusCodeir = random.nextInt(statusCodelist.size());
//			System.out.println(proper_noun[index]);

			MyCase myCases = new MyCase();
			myCases.setTitle(faker.book().title());
			myCases.setStatus(statuslist.get(statusir));

			myCases.setCaseType1(caseType1list.get(caseType1ir));
			myCases.setCaseType2(caseType2list.get(caseType2ir));
			myCases.setCaseType3(caseType3list.get(caseType3ir));
			myCases.setStatusCode(statusCodelist.get(statusCodeir));
			myCases.setComments(faker.lorem().paragraph(2));

			myCasesList.add(myCases);
		});
		myCasesDAO.saveAll(myCasesList);

//		List<MyCasesCodeGroups> myCasesCodeGroupsList = new ArrayList<MyCasesCodeGroups>();
//		myCasesList.forEach(myCases -> {
//			MyCasesCodeGroups mystatus = new MyCasesCodeGroups();
//			mystatus.setMyCases(myCases);
//			
//			int statusir = random.nextInt(statuslist.size());
//			mystatus.setStatus(statuslist.get(statusir));
//
//			int caseType1ir = random.nextInt(caseType1list.size());
//			mystatus.setCaseType1(caseType1list.get(caseType1ir));
//			
//			myCasesCodeGroupsList.add(mystatus);
//		});
//		myCasesCodeGroupsDAO.saveAll(myCasesCodeGroupsList);

//		List<MyCasesBean> list = myCasesDAO.findAllForList(PageRequest.of(0, 25));
//		jInfo(list);AO.findAllForList(PageRequest.of(0, 25));
//		jInfo(list);
		//System.out.println("#####################################");
//		jInfo(myCasesList.get(0));
//		jInfo(myCasesDAO.findById(myCasesList.get(0).getId()).get());
//		jInfo(myCasesDAO.findById(myCasesList.get(1).getId()).get());
//		jInfo(myCasesDAO.findById(myCasesList.get(2).getId()).get());
		//System.out.println("-------------------------------------");
		// jInfo(myCasesDAO.findAll());
		//Page list = myCasesDAO.findAll(PageRequest.of(0, 5));
		//toJson(list);
		//System.out.println("#####################################");

//		List<Author> authors = new ArrayList<Author>();
//		for (int i = 0; i < 5; i++) {
//			authors.add(new Author(i, faker.book().author(), null));
//		}
//		List<Book> books = new ArrayList<Book>();
//		Calendar cal = new GregorianCalendar(2000, 0, 1);
//		Date from = cal.getTime();
//		Date to = new Date();
//		for (int i = 0; i < 100; i++) {
//			Set<Author> authorSet = new HashSet<Author>();
//			int iauthor = random.nextInt(authors.size());
//			int iauthor2 = random.nextInt(authors.size());
//			authorSet.add(authors.get(iauthor));
//			authorSet.add(authors.get(iauthor2));
//
//			books.add(new Book(i, faker.book().title(), faker.date().between(from, to), authorSet));
//		}
//
//		String title = bookDAO.findById(new Long(1)).get().getTitle();
//		System.out.println(title);
//		List<Author> authorList = authorHibernateDAOSupport.getAuthors(title);
//		toJson(authorList);
		System.out.println("DataLoader.load - END");
	}

	private List<CodeGroups> addCodeGroup(final String[] codeGroup, String code) {
		List<CodeGroups> codeGroupslist = new ArrayList<CodeGroups>();
		for (String value : codeGroup) {
			CodeGroups codeGroups = new CodeGroups();
			codeGroups.setCode(code);
			codeGroups.setValue(value);
			codeGroups.setDescription(value);
			codeGroupslist.add(codeGroups);
		}
		return codeGroupslist;
	}

	private void toJson(Object obj) {
		try {
			ObjectMapper jsonObjMap = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
			String json = jsonObjMap.writeValueAsString(obj);
			System.out.println("jInfo:\n" + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
