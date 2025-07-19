/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.university.course.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.university.course.model.Course;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Course in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CourseCacheModel implements CacheModel<Course>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CourseCacheModel)) {
			return false;
		}

		CourseCacheModel courseCacheModel = (CourseCacheModel)object;

		if (courseId == courseCacheModel.courseId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, courseId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{courseId=");
		sb.append(courseId);
		sb.append(", courseName=");
		sb.append(courseName);
		sb.append(", description=");
		sb.append(description);
		sb.append(", credits=");
		sb.append(credits);
		sb.append(", students=");
		sb.append(students);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Course toEntityModel() {
		CourseImpl courseImpl = new CourseImpl();

		courseImpl.setCourseId(courseId);

		if (courseName == null) {
			courseImpl.setCourseName("");
		}
		else {
			courseImpl.setCourseName(courseName);
		}

		if (description == null) {
			courseImpl.setDescription("");
		}
		else {
			courseImpl.setDescription(description);
		}

		courseImpl.setCredits(credits);
		courseImpl.setStudents(students);

		courseImpl.resetOriginalValues();

		return courseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		courseId = objectInput.readLong();
		courseName = objectInput.readUTF();
		description = objectInput.readUTF();

		credits = objectInput.readInt();

		students = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(courseId);

		if (courseName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(courseName);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(credits);

		objectOutput.writeInt(students);
	}

	public long courseId;
	public String courseName;
	public String description;
	public int credits;
	public int students;

}