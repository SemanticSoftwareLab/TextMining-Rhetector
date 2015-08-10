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

package info.semanticsoftware.rhetector.transducer;

import gate.creole.Transducer;
import gate.creole.metadata.CreoleParameter;
import gate.creole.metadata.CreoleResource;
import gate.creole.metadata.HiddenCreoleParameter;
import java.net.URL;

/**
 * The transducer for the Rhetector component.
 * This is a JAPE transducer and this class is here to allow the specification
 * in creole.xml of a default grammar to be used in the PR.
 * @author Bahar Sateli
 */
@CreoleResource(name = "Rhetorical Entity Transducer",
  comment = "Rhetorical Entity Extraction",
  helpURL = "http://www.semanticsoftware.info/rhetector/",
  icon = "jape"
  )
@SuppressWarnings("PMD")
public class RETransducer extends Transducer {

  /**
   * The ontology parameter is not used for this PR and therefore hidden.
   * 
   * @param ontology
   */
  @HiddenCreoleParameter
  @Override
  public void setOntology(gate.creole.ontology.Ontology ontology) {
    super.setOntology(ontology);
  }

  /**
   * The binaryGrammarURL parameter is not used for this PR and therefore hidden.
   * 
   * @param url
   */
  @HiddenCreoleParameter
  @Override
  public void setBinaryGrammarURL(URL url) {
    super.setBinaryGrammarURL(url);
  }


  /**
   * The grammarURL parameter provides the main.jape file as a default
   * for this PR.
   * 
   * @param newGrammarURL
   */
  @CreoleParameter(
    comment = "The URL of the main grammar file.",
    suffixes = "jape",
    defaultValue = "resources/grammars/metadiscourse_detection/main.jape"
  )
  @Override
  public void setGrammarURL(java.net.URL newGrammarURL) {
    super.setGrammarURL(newGrammarURL);
  }
}
