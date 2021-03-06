/*
 * generated by Xtext 2.12.0
 */
package ctwedge.ui.wizard


import com.google.inject.Inject
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.resource.FileExtensionProvider

class CTWedgeNewProjectWizardInitialContents {
	@Inject
	FileExtensionProvider fileExtensionProvider

	def generateInitialContents(IFileSystemAccess2 fsa) {
		fsa.generateFile(
			"src/model/Model." + fileExtensionProvider.primaryFileExtension,
			'''
			/*
			 * This is an example model
			 */
			Model Phone
			 Parameters:
			   emailViewer : Boolean
			   textLines:  [ 25 .. 30 ]
			   display : {16MC, 8MC, BW}
			
			 Constraints:
			   # emailViewer => textLines > 28 #
			'''
			)
	}
}
