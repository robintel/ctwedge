package ctwedge.eclipse.ui.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

import ctwedge.ctWedge.CitModel;
import ctwedge.eclipse.util.Constants;
import ctwedge.util.ext.ICTWedgeModelProcessor;
import ctwedge.util.ext.ICTWedgeTestGenerator;

public class CTwedgeLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {
	
	@Override
	public void launch(ILaunchConfiguration configuration, String arg1, ILaunch arg2, IProgressMonitor arg3) throws CoreException {
		System.out.println(configuration + " " + arg1 + arg2);
		System.out.println(configuration.getAttribute(CTWedgeTab.STRENGTH, 2) + " " + configuration.getAttribute(CTWedgeTab.GENERATOR2, "null") + " " + configuration.getAttribute(CTWedgeTab.IGNORE_CONSTRAINTS,false));

		String generatorName = configuration.getAttribute(CTWedgeTab.GENERATOR2, "null");
		int nWise = configuration.getAttribute(CTWedgeTab.STRENGTH, 2);
		boolean ignoreConstraints = configuration.getAttribute(CTWedgeTab.IGNORE_CONSTRAINTS,false);

		String filePath = configuration.getAttribute(CTWedgeLaunchShortcut.ATTR_FILEPATH, "");
		
		//String filePath = configuration.getAttribute("file", "nullInputFile");
		
		//Cerco generatore con nome generatorName
		IConfigurationElement[] eXgenerator = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(Constants.packagaName + ".ctwedgeGenerators");
		
		BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
			private CitModel citModel;

			@Override
			public void run() {
				ISafeRunnable runnable = null;
				for (final IConfigurationElement e : eXgenerator) {
					if (e.getAttribute("Name").equals(generatorName)) {
						Object o = null;
						try {
							o = e.createExecutableExtension("GeneratorPrototype");
						} catch (CoreException e1) {
							e1.printStackTrace();
						}
						if (o instanceof ICTWedgeTestGenerator) {
							runnable = new ISafeRunnable() {

								@Override
								public void handleException(Throwable exception) {
									System.out.println("Exception in client");
								}

								@Override
								public void run() throws Exception {
									citModel = ICTWedgeModelProcessor.getModel(filePath);
								}
							};

							SafeRunner.run(runnable);
							if (citModel != null) {
								Job generation = new SafeGeneratorRunnable("Generation of the test suite", nWise, citModel, ignoreConstraints, generatorName, (ICTWedgeTestGenerator)o);
								generation.setPriority(Job.SHORT);
								generation.setUser(true);
								generation.schedule();
							} else
								//MR: prima era "showMessage", ma non riesco a farlo perché non c'è il parent
								System.out.println("The ComB CitModel is not valid");
							break;
						}
					}
				}
			}
		});				
	}
}
