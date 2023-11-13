package org.uid.impl;


import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("unique identifier test suite")
@SelectPackages("org.uid")
@IncludeClassNamePatterns(".*Test")
public class UniqueIdentifierTestSuite {
}
