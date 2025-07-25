/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.university.course.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.university.course.exception.NoSuchCourseException;
import com.university.course.model.Course;
import com.university.course.service.CourseLocalServiceUtil;
import com.university.course.service.persistence.CoursePersistence;
import com.university.course.service.persistence.CourseUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CoursePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.university.course.service"));

	@Before
	public void setUp() {
		_persistence = CourseUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Course> iterator = _courses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Course course = _persistence.create(pk);

		Assert.assertNotNull(course);

		Assert.assertEquals(course.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Course newCourse = addCourse();

		_persistence.remove(newCourse);

		Course existingCourse = _persistence.fetchByPrimaryKey(
			newCourse.getPrimaryKey());

		Assert.assertNull(existingCourse);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCourse();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Course newCourse = _persistence.create(pk);

		newCourse.setCourseName(RandomTestUtil.randomString());

		newCourse.setDescription(RandomTestUtil.randomString());

		newCourse.setCredits(RandomTestUtil.nextInt());

		newCourse.setStudents(RandomTestUtil.nextInt());

		_courses.add(_persistence.update(newCourse));

		Course existingCourse = _persistence.findByPrimaryKey(
			newCourse.getPrimaryKey());

		Assert.assertEquals(
			existingCourse.getCourseId(), newCourse.getCourseId());
		Assert.assertEquals(
			existingCourse.getCourseName(), newCourse.getCourseName());
		Assert.assertEquals(
			existingCourse.getDescription(), newCourse.getDescription());
		Assert.assertEquals(
			existingCourse.getCredits(), newCourse.getCredits());
		Assert.assertEquals(
			existingCourse.getStudents(), newCourse.getStudents());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Course newCourse = addCourse();

		Course existingCourse = _persistence.findByPrimaryKey(
			newCourse.getPrimaryKey());

		Assert.assertEquals(existingCourse, newCourse);
	}

	@Test(expected = NoSuchCourseException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Course> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CollegeCourse_Course", "courseId", true, "courseName", true,
			"description", true, "credits", true, "students", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Course newCourse = addCourse();

		Course existingCourse = _persistence.fetchByPrimaryKey(
			newCourse.getPrimaryKey());

		Assert.assertEquals(existingCourse, newCourse);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Course missingCourse = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCourse);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Course newCourse1 = addCourse();
		Course newCourse2 = addCourse();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCourse1.getPrimaryKey());
		primaryKeys.add(newCourse2.getPrimaryKey());

		Map<Serializable, Course> courses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, courses.size());
		Assert.assertEquals(
			newCourse1, courses.get(newCourse1.getPrimaryKey()));
		Assert.assertEquals(
			newCourse2, courses.get(newCourse2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Course> courses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(courses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Course newCourse = addCourse();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCourse.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Course> courses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, courses.size());
		Assert.assertEquals(newCourse, courses.get(newCourse.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Course> courses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(courses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Course newCourse = addCourse();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCourse.getPrimaryKey());

		Map<Serializable, Course> courses = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, courses.size());
		Assert.assertEquals(newCourse, courses.get(newCourse.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CourseLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Course>() {

				@Override
				public void performAction(Course course) {
					Assert.assertNotNull(course);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Course newCourse = addCourse();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Course.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("courseId", newCourse.getCourseId()));

		List<Course> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Course existingCourse = result.get(0);

		Assert.assertEquals(existingCourse, newCourse);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Course.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("courseId", RandomTestUtil.nextLong()));

		List<Course> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Course newCourse = addCourse();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Course.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("courseId"));

		Object newCourseId = newCourse.getCourseId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("courseId", new Object[] {newCourseId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCourseId = result.get(0);

		Assert.assertEquals(existingCourseId, newCourseId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Course.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("courseId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"courseId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Course addCourse() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Course course = _persistence.create(pk);

		course.setCourseName(RandomTestUtil.randomString());

		course.setDescription(RandomTestUtil.randomString());

		course.setCredits(RandomTestUtil.nextInt());

		course.setStudents(RandomTestUtil.nextInt());

		_courses.add(_persistence.update(course));

		return course;
	}

	private List<Course> _courses = new ArrayList<Course>();
	private CoursePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}