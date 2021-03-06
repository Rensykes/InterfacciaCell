package rinocitologia;

import java.io.File;
import java.util.*;

/**
 * Contains informations meaningful for the diagnosis such as an HashMap in the format:
 * <br>
 * <String, Cellule> => <Cell Name, Cell Informations>
 * <br>
 * Cell Informations contains the count of the cells (done by neural network) and the grade transposition.
 * <br>
 * Contains methods useful to manipulate those informations.
 */
public class Patient {
	private String firstName; //????????
	private String surname; //??????
	private Map<String, Cell> dictionary;
	private String comuneNascita;
	private String comuneResidenza;
	private String path;
	private String pathData = System.getProperty("user.home") + File.separator + "data";
	private String pathInput;
	private String pathCellule;
	private String pathCFPhoto;
	private String pathCampi;
	private String pathBiofilmsi;
	private String pathBiofilmno;
	private Boolean extractedCells;
	private CodiceFiscale cf;
	private String terapia;
	private String diagnosiUfficiale;
	private ArrayList<Anamnesi> anamnesiList = new ArrayList<>();
	private ArrayList<Diagnosi> diagnosi = new ArrayList<>();

	public Patient() {
		dictionary = new HashMap<String, Cell>();
		final File folderData = new File(pathData);
		if(!folderData.exists() && !folderData.mkdir()) {
			//failed to create the folder, probably exit
			throw new RuntimeException("Failed to create save directory.");
		}




		final File folder = new File(pathData , "Pinco_Pallino");
		if(!folder.exists() && !folder.mkdir()) {
			   //failed to create the folder, probably exit
			   throw new RuntimeException("Failed to create save directory.");
		}
		this.path = folder.getAbsolutePath();
		this.pathInput = path + File.separator + "inputs";
		this.pathCellule = pathInput + File.separator + "cellule";
		this.pathBiofilmsi = pathInput + File.separator + "biofilmsi";
		this.pathBiofilmno = pathInput + File.separator + "biofilmno";
		System.out.print(path);

	}


	public Patient(String name, String surname) {
		dictionary = new HashMap<String, Cell>();
		this.firstName = name;
		this.surname = surname;
		final File folderData = new File(pathData);
		if(!folderData.exists() && !folderData.mkdir()) {
			//failed to create the folder, probably exit
			throw new RuntimeException("Failed to create save directory.");
		}
		final File folder = new File(pathData , name + "_" + surname);
		if(!folder.exists() && !folder.mkdir()) {
			   //failed to create the folder, probably exit
			   throw new RuntimeException("Failed to create save directory.");
		}
		this.path = folder.getAbsolutePath();
		this.pathInput = path + File.separator + "inputs";
		this.pathCellule = pathInput + File.separator + "cellule";
		this.pathBiofilmsi = pathInput + File.separator + "biofilmsi";
		this.pathBiofilmno = pathInput + File.separator + "biofilmno";

	}

	//public void setAnamnesi(Anamnesi anamnesi) { this.anamnesi = anamnesi; }

	//public Anamnesi getAnamnesi() {return anamnesi;}

	public void addAnamnesi(Anamnesi anamnesi) { this.anamnesiList.add(anamnesi); }

	public ArrayList<Anamnesi> getAnamnesiList() {return anamnesiList;}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Map<String, Cell> getDictionary() {
		return dictionary;
	}

	public void setDictionary(Map<String, Cell> dictionary) {
		this.dictionary = dictionary;
	}

	public String getPath() { return path;	}

	public void setPath(String path) { this.path = path; }

	public void setNewPath(){this.path = pathData + File.separator + cf.getCF();}

	public String getPathData() { return pathData; }

	public void setPathData(String folderPath) { this.pathData = folderPath; }

	public void setCf(CodiceFiscale cf) {this.cf = cf;}

	public CodiceFiscale getCf() {return cf;}

	public String getPathInput() { return pathInput; }

	public Anamnesi getLastAnamnesi(){
		Anamnesi item = null;
		if (anamnesiList != null && !anamnesiList.isEmpty()) {
			item = anamnesiList.get(anamnesiList.size()-1);
		}
		return item;
	}

	public void setDiagnosi(ArrayList<Diagnosi> diagnosis) {
		this.diagnosi = diagnosis;
	}

	public ArrayList<Diagnosi> getDiagnosi() { return diagnosi; }

	public String getTerapia() { return terapia; }

	public void setTerapia(String terapia) { this.terapia = terapia; }

	public String getDiagnosiUfficiale() { return diagnosiUfficiale; }

	public String getPathCellule() {return  pathCellule;}

	public void setDiagnosiUfficiale(String diagnosiUfficiale) { this.diagnosiUfficiale = diagnosiUfficiale; }

	public String getPathCFPhoto() {
		return pathCFPhoto;
	}

	public String getPathCampi() {
		return pathCampi;
	}

	public void setPathCampi(String pathCampi) {
		this.pathCampi = pathCampi;
	}

	public Boolean getExtractedCells() {
		return extractedCells;
	}

	public void setExtractedCells(Boolean extractedCells) {
		this.extractedCells = extractedCells;
	}

	public void setPathCFPhoto(String pathCFPhoto) {
		this.pathCFPhoto = pathCFPhoto;
	}

	public String getPathBiofilmsi() { return pathBiofilmsi; }

	public void setPathBiofilmsi(String pathBiofilmsi) { this.pathBiofilmsi = pathBiofilmsi; }

	public String getPathBiofilmno() { return pathBiofilmno; }

	public void setPathBiofilmno(String getPathBiofilmno) { this.pathBiofilmno = getPathBiofilmno; }


	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public String getComuneResidenza() {
		return comuneResidenza;
	}

	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}



	/**
	 * Rename patient folder and saves path to path variable
	 */
	public void rename(){
		final File folder = new File(path);
		if(!folder.exists() && !folder.mkdir()) {
			//failed to create the folder, probably exit
			throw new RuntimeException("Failed to create save directory.");
		}
		File newDir = new File(pathData, cf.getCF());
		folder.renameTo(newDir);
		this.path = newDir.getAbsolutePath();
		this.pathInput = path + File.separator + "inputs";
		this.pathCellule = pathInput + File.separator + "cellule";
		this.pathBiofilmsi = pathInput + File.separator + "biofilmsi";
		this.pathBiofilmno = pathInput + File.separator + "biofilmno";
		System.out.print(path);
		System.out.println(pathInput);
	}

	/**
	 * Put an element in the HashMap (dictionary).
	 * Since there could be some missunderstanding in the format of the name, it will be lower cased and then capitalized just the first letter.
	 * @param nome String that contains name of the cell.
	 * @param numeroCellule Integer that contains the number of the cells (for each type) retrieved by neural network.
	 */
	public void addElement(String nome, int numeroCellule) {
		String actualName = nome.toLowerCase().substring(0,1).toUpperCase() + nome.toLowerCase().substring(1);
		dictionary.put(actualName, new Cell(nome, numeroCellule));
		
	}

	/**
	 * Automatically counts cells after revision.
	 */
	public void addAllElements(){
		if(new File(pathInput, "eosinofili").list().length == 0){
			dictionary.put("Eosinofili", new Cell("Eosinofili", 0));
			System.out.println("Eosinofili 0");

		} else {
			dictionary.put("Eosinofili", new Cell("Eosinofili", new File(pathInput, "eosinofili").list().length - 1));
			System.out.println("Eosinofili " + Integer.toString(new File(pathInput, "eosinofili").list().length - 1));

		}
		if(new File(pathInput, "epiteliali").list().length == 0){
			dictionary.put("Epiteliali", new Cell("Epiteliali", 0));
			System.out.println("Epiteliali 0");

		} else {
			dictionary.put("Epiteliali", new Cell("Epiteliali", new File(pathInput, "epiteliali").list().length - 1));
			System.out.println("Epitelali " + Integer.toString(new File(pathInput, "epiteliali").list().length - 1));

		}
		if(new File(pathInput, "linfociti").list().length == 0){
			dictionary.put("Linfociti", new Cell("Linfociti", 0));
			System.out.println("Linfociti 0");

		} else {
			dictionary.put("Linfociti", new Cell("Linfociti", new File(pathInput, "linfociti").list().length - 1));
			System.out.println("Linfociti " + Integer.toString(new File(pathInput, "linfociti").list().length - 1));

		}
		if(new File(pathInput, "mastcellule").list().length == 0){
			dictionary.put("Mastcellule", new Cell("Mastcellule", 0));
			System.out.println("Mastcellule 0");

		} else {
			dictionary.put("Mastcellule", new Cell("Mastcellule", new File(pathInput, "mastcellule").list().length - 1));
			System.out.println("Mastcellule " + Integer.toString(new File(pathInput, "mastcellule").list().length - 1));

		}
		if(new File(pathInput, "mucipare").list().length == 0){
			dictionary.put("Mucipare", new Cell("Mucipare", 0));
			System.out.println("Mucipare 0");

		} else {
			dictionary.put("Mucipare", new Cell("Mucipare", new File(pathInput, "mucipare").list().length - 1));
			System.out.println("Mucipare " + Integer.toString(new File(pathInput, "mucipare").list().length - 1));
		}
		if(new File(pathInput, "neutrofili").list().length == 0){
			dictionary.put("Neutrofili", new Cell("Neutrofili", 0));
			System.out.println("Neutrofili 0");
		} else {
			dictionary.put("Neutrofili", new Cell("Neutrofili", new File(pathInput, "neutrofili").list().length - 1));
			System.out.println("Neutrofili " + Integer.toString(new File(pathInput, "neutrofili").list().length - 1));
		}
		if(new File(pathInput, "others").list().length == 0){
			dictionary.put("Others", new Cell("Others", 0));
			System.out.println("Others 0");

		} else {
			dictionary.put("Others", new Cell("Others", new File(pathInput, "others").list().length - 1));
			System.out.println("Others " + Integer.toString(new File(pathInput, "others").list().length - 1));

		}
	}
	
	/**
	 * Autofills the dictionary with the missing informations from neural network.
	 * It is filled with missing cells with a count of 0.
	 */
	public void completeDictionary() {
		List<String> cellsList = Arrays.asList("Ciliate", "Mucipare", "Metaplastiche", "Neutrofili", "Eosinofili", "Mastociti", "Linfociti", "Batteri", "Spore", "Macchia", "Epiteliali");
		String cellName;
		Iterator<String> i = cellsList.iterator();
		while(i.hasNext()) {
			cellName = i.next();
			Cell cell = dictionary.get(cellName);
			if(cell == null) {
				dictionary.put(cellName, new Cell(0,0));
			}
		}
	}

	/**
	 * Returns Anamnesi object based on the date when it was created.
	 * @param time
	 * @return
	 */
	public Anamnesi getAnamnesi(String time){
		Anamnesi anam = new Anamnesi();
		for(Anamnesi anamnesi: anamnesiList){
			if (anamnesi.getTime() == time)
				anam = anamnesi;
		}
		return anam;
	}
	
	@Override
	public String toString() {
		String name = "First Name: " + firstName + "\nSurname: " + surname + "\nNato a: " + comuneNascita + "\nResidente a: " + comuneResidenza + "\n\n";
		StringBuilder cells = new StringBuilder("\nCells List:\n");
		for (Map.Entry<String, Cell> entry : dictionary.entrySet()) {
			cells.append("Name: " + entry.getKey() + " - Count: " + entry.getValue().getcellCount() + " - Grade: " + entry.getValue().getgrade() + ";\n");
		}
		if(cf != null)
			return name + "Il: " + cf.getDay() + "/" + cf.getMonth() + "/" + cf.getYear() + "\nSesso: " + cf.getSex() + "\nCodice fiscale: " + cf.getCF() + "\n" + cells.toString() ;
		else
			return name + cells.toString();
	}
}


