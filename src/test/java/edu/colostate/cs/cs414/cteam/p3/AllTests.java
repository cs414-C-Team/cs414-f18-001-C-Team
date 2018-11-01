package edu.colostate.cs.cs414.cteam.p3;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"edu.colostate.cs.cs414.cteam.p3.model","edu.colostate.cs.cs414.cteam.p3.view","edu.colostate.cs.cs414.cteam.p3.controller"})
public class AllTests
{
}