/*
 * Copyright (c) 2020 Livio, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * Neither the name of the Livio Inc. nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.smartdevicelink.test.rpc.enums;

import com.smartdevicelink.proxy.rpc.enums.VehicleDataActiveStatus;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a unit test class for the SmartDeviceLink library project class :
 * {@link com.smartdevicelink.proxy.rpc.enums.VehicleDataActiveStatus}
 */
public class VehicleDataActiveStatusTests extends TestCase {

	/**
	 * Verifies that the enum values are not null upon valid assignment.
	 */
	public void testValidEnums () {
		String example = "INACTIVE_NOT_CONFIRMED";
		VehicleDataActiveStatus enumInactiveNotConfirmed = VehicleDataActiveStatus.valueForString(example);
		example = "INACTIVE_CONFIRMED";
		VehicleDataActiveStatus enumInactiveConfirmed = VehicleDataActiveStatus.valueForString(example);
		example = "ACTIVE_NOT_CONFIRMED";
		VehicleDataActiveStatus enumActiveNotConfirmed = VehicleDataActiveStatus.valueForString(example);
		example = "ACTIVE_CONFIRMED";
		VehicleDataActiveStatus enumActiveConfirmed = VehicleDataActiveStatus.valueForString(example);
		example = "FAULT";
		VehicleDataActiveStatus enumFault = VehicleDataActiveStatus.valueForString(example);

		assertNotNull("INACTIVE_NOT_CONFIRMED returned null", enumInactiveNotConfirmed);
		assertNotNull("INACTIVE_CONFIRMED returned null", enumInactiveConfirmed);
		assertNotNull("ACTIVE_NOT_CONFIRMED returned null", enumActiveNotConfirmed);
		assertNotNull("ACTIVE_CONFIRMED returned null", enumActiveConfirmed);
		assertNotNull("FAULT returned null", enumFault);
	}

	/**
	 * Verifies that an invalid assignment is null.
	 */
	public void testInvalidEnum () {
		String example = "FauLt";
		try {
			VehicleDataActiveStatus temp = VehicleDataActiveStatus.valueForString(example);
			assertNull("Result of valueForString should be null.", temp);
		}
		catch (IllegalArgumentException exception) {
			fail("Invalid enum throws IllegalArgumentException.");
		}
	}

	/**
	 * Verifies that a null assignment is invalid.
	 */
	public void testNullEnum () {
		String example = null;
		try {
			VehicleDataActiveStatus temp = VehicleDataActiveStatus.valueForString(example);
			assertNull("Result of valueForString should be null.", temp);
		}
		catch (NullPointerException exception) {
			fail("Null string throws NullPointerException.");
		}
	}

	/**
	 * Verifies the possible enum values of VehicleDataActiveStatus.
	 */
	public void testListEnum() {
		List<VehicleDataActiveStatus> enumValueList = Arrays.asList(VehicleDataActiveStatus.values());

		List<VehicleDataActiveStatus> enumTestList = new ArrayList<>();
		enumTestList.add(VehicleDataActiveStatus.INACTIVE_NOT_CONFIRMED);
		enumTestList.add(VehicleDataActiveStatus.INACTIVE_CONFIRMED);
		enumTestList.add(VehicleDataActiveStatus.ACTIVE_NOT_CONFIRMED);
		enumTestList.add(VehicleDataActiveStatus.ACTIVE_CONFIRMED);
		enumTestList.add(VehicleDataActiveStatus.FAULT);

		assertTrue("Enum value list does not match enum class list",
				enumValueList.containsAll(enumTestList) && enumTestList.containsAll(enumValueList));
	}
}