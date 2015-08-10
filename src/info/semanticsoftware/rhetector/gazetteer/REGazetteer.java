/*
 * Rhetorical Entity Detector -- http://www.semanticsoftware.info/rhetector
 *
 * This file is part of the Rhetector component.
 *
 * Copyright (c) 2015, Semantic Software Lab, http://www.semanticsoftware.info
 *    Rene Witte
 *    Bahar Sateli
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.semanticsoftware.rhetector.gazetteer;

import java.net.URL;

import gate.creole.metadata.CreoleParameter;
import gate.creole.metadata.CreoleResource;

import com.ontotext.gate.gazetteer.HashGazetteer;

/**
 * The gazetteer for the Rhetector component.
 * This is a gazetteer PR and this class is here to allow the specification
 * in creole.xml of a default list to be used in the PR.
 * @author Bahar Sateli
 */
@CreoleResource(name = "Rhetorical Entity Gazetteer",
comment = "Rhetorical Entity Gazetteer",
helpURL = "http://www.semanticsoftware.info/rhetector/",
icon = "gazetteer"
)
public class REGazetteer extends HashGazetteer {

	/**
	   * The newListURL parameter provides the path to the main gazetteer file as a default
	   * for this PR.
	   * 
	   * @param newListURL
	   */
	  @CreoleParameter(
	    comment = "The URL of the main gazetteer file.",
	    suffixes = "def",
	    defaultValue = "resources/gazetteers/lists.def"
	  )
	  @Override
	  public void setListsURL(URL newListURL){
		  super.setListsURL(newListURL);
	  }
}
