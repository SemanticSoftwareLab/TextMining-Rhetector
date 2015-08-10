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

package info.semanticsoftware.rhetector;

import gate.*;
import gate.util.GateException;
import gate.util.OffsetComparator;
import gate.util.ExtensionFileFilter;
import gate.util.persistence.PersistenceManager;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Bahar Sateli
 */
public class REExtractionTest {
  private static CorpusController rhetectorApp;
  private Corpus testCorpus;

  @BeforeClass 
  public static void loadApp() throws MalformedURLException, IOException, GateException  {
    if (!Gate.isInitialised()) {
      Gate.init();
    }    

    Properties sysProps = System.getProperties();
    String appName = sysProps.getProperty("reextraction.app.name");
    rhetectorApp = (CorpusController) PersistenceManager.loadObjectFromFile(
	        new File(appName));
  }

  @Before
  public void setUp() throws GateException  {
    testCorpus = Factory.newCorpus("Test corpus");
    rhetectorApp.setCorpus(testCorpus);
  }

  @After
  public void tearDown() throws Exception {
    Factory.deleteResource(testCorpus);
  }

  @AfterClass
  public static void unloadApp() throws Exception {
    Factory.deleteResource(rhetectorApp);
  }

  @Test
  public void testContribution() throws Exception{
      Document testDoc = Factory.newDocument("In this paper we present our awesome ideas.");
      try {
        testCorpus.add(testDoc);
        try {
          rhetectorApp.execute();

          // Check the results
          AnnotationSet annots = testDoc.getAnnotations();
          assertNotNull("test document has no annotations!", annots);
            
          AnnotationSet REs = annots.get("RhetoricalEntity");
          assertNotNull("test document has no RhetoricalEntity annotations!", REs);

          List<Annotation> npList = new ArrayList<Annotation>(REs);
          // sort in document order
          Collections.sort(npList, new OffsetComparator());
          assertEquals("Document should have one RhetoricalEntity", npList.size(), 1);

          Annotation np = npList.get(0);
          assertEquals("The RhetoricalEntity type must be 'Contribution' from the SALT Ontology.",
		       "http://salt.semanticauthoring.org/ontologies/sro#Contribution", getRETypeString(np));

        } finally {
          testCorpus.remove(testDoc);
        }
      } finally {
        Factory.deleteResource(testDoc);
      }
  }

  private static String getRETypeString(Annotation ann) throws Exception {
    FeatureMap features = ann.getFeatures();
    return (String)features.get("URI1");
  }

}
