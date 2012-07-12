package fr.ippon.gitclient;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Repository repository = getSampleRepo();
		new Poller(repository, new Notifier()).run();
	}

	public static Repository getSampleRepo() {
		String gitDirPath = "../sampleGitRepo/.git";
//		String gitDirPath = "/home/jcdelmas/dev/test-git-repo/.git";
		try {
			return getRepository(gitDirPath);
		} catch(IOException e) {
			e.printStackTrace();	
			throw new RuntimeException("Le repo d'exemple n'existe pas encore. Vous pouvez le créer via le script shell initSampleGitRepo.sh à la racine du projet");
		}
	}
	public static FileRepository getRepository(String gitDirPath)
			throws IOException {
		return new FileRepositoryBuilder()
			.setGitDir(new File(gitDirPath))
			.setMustExist(true)
			.build();
	}

}
