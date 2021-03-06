/*
 * Copyright 2013 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.config.cli.command.impl;

import java.util.Scanner;

import org.jboss.weld.environment.se.WeldContainer;
import org.kie.config.cli.CliContext;
import org.kie.config.cli.command.CliCommand;
import org.uberfire.backend.group.Group;
import org.uberfire.backend.group.GroupService;

public class RemoveGroupCliCommand implements CliCommand {

	@Override
	public String getName() {
		return "remove-group";
	}

	@Override
	public String execute(CliContext context) {
		StringBuffer result = new StringBuffer();
		WeldContainer container = context.getContainer();

		GroupService groupService = container.instance().select(GroupService.class).get();
		
		Scanner input = context.getInput();
		System.out.print(">>Group name:");
		String name = input.nextLine();
		
		Group group = groupService.getGroup(name);
		if (group == null) {
			return "No group " + name + " was found, exiting";
		}
		groupService.removeGroup(name);
		result.append("Group " + name + " was removed successfully");
		
		return result.toString();
	}

}
