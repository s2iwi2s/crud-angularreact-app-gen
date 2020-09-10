import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CrudeGen2 {
	public CrudeGen2() {
		System.out.println("CrudeGen2 - STARTED...");
		//gen("CrudeGen_java.json");
		gen("CrudeGen_react.json");
		//gen("CrudeGen_angular.json");
		
		System.out.println("CrudeGen2 - DONE!!!");
	}

	private void gen(String jsonFileName) {
		JSONParser parser = new JSONParser();
		try {
			Object data = parser.parse(new FileReader(jsonFileName));
			JSONObject crudeGenobj = (JSONObject) data;
			JSONArray tmplsAry = (JSONArray) crudeGenobj.get("TMPLS");

			JSONArray objectAry = (JSONArray) crudeGenobj.get("OBJECTS");

			for (Object objectItem : objectAry) {
				JSONObject jsonObject = (JSONObject) objectItem;
				String name = jsonObject.get("NAME").toString();
				JSONArray fieldsJson = (JSONArray) jsonObject.get("FIELDS");

				for (Object tmplObj : tmplsAry) {
					JSONObject jsonSetup = (JSONObject) tmplObj;
					JSONArray subTmplsJson = (JSONArray) jsonSetup.get("SUB_TMPL");

					String tmplFile = (String) jsonSetup.get("TEMPLATE_FILE");
					String output = (String) jsonSetup.get("OUTPUT");


					output = replaceText("XYCLASSYX", name, output);
					output = replaceText("XYclassYX", StringUtils.uncapitalize(name), output);
					output = replaceText("XYclass-kebabYX", toKebab(StringUtils.uncapitalize(name)), output);

					System.out.println("output: " + output);
					File fileout = new File(output);
					if (!fileout.getParentFile().exists()) {
						fileout.getParentFile().mkdirs();
					}
					createFile(name, tmplFile, output);
					updateFileContents(subTmplsJson, name, fieldsJson, crudeGenobj, output);

					File tmp = new File(output + ".tmp");
					tmp.delete();

				}
			}

			// jsonArray.entrySet().forEach(System.out::println);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private String replaceText(String key, String name, String output) {
		if (output.indexOf(key) != -1) {
			output = output.replaceAll(key, name);
		}
		return output;
	}

	private void updateFileContents(JSONArray subTmplsJsonAry, String name, JSONArray fieldsJsonAry,
			JSONObject crudeGenobj, String output) {
//		JSONArray subTmpltAry = (JSONArray) crudeGenobj.get("SUB_TMPL_FILES");
		JSONObject fieldMapJsonObj = (JSONObject) crudeGenobj.get("FIELD_MAP");

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(output))) {
			StringBuilder contentBuildr = getFileContent(output + ".tmp");
			String contents = contentBuildr.toString();
//			Map<String, StringBuilder> buildrMap = new HashMap<String, StringBuilder>();

			// loop SETUP / SUB_TMPL

			for (int idx = 0; subTmplsJsonAry != null && idx < subTmplsJsonAry.size(); idx++) {
				// TMPL TMPL_FILE TMPL_FILTER
				JSONObject subTmplJson = (JSONObject) subTmplsJsonAry.get(idx);
				String tmplKey = (String) subTmplJson.get("TMPL_KEY");
				String tmplFile = (String) subTmplJson.get("TMPL_FILE");
				String tmplFileList = (String) subTmplJson.get("TMPL_FILE_LIST");

				// TMPLS / SUB_TMPL / TMPL_FILE contents

//				System.out.println("tmplFile=>" + tmplFile);
//				System.out.println("tmplSubContentTemp=>" + tmplSubContentTemp);
				StringBuilder tmplSubContentBuildr = new StringBuilder();
				// loop TMPLS / FIELDS
				for (Object fieldsJsonObj : fieldsJsonAry) {
					JSONObject fieldsJson = (JSONObject) fieldsJsonObj;

					String listType = (String) fieldsJson.get("LIST_TYPE");
					String tmplSubContent = "";
					if (listType != null && tmplFileList != null) {
						tmplSubContent = getFileContent(tmplFileList).toString();
					} else {
						tmplSubContent = getFileContent(tmplFile).toString();
					}

					// TMPLS / SUB_TMPL / TMPL_FILE contents
					tmplSubContent = tmplSubContent.replaceAll(fieldMapJsonObj.get("CLASS_NAME").toString(),
							name.replaceAll(" ", ""));
					tmplSubContent = tmplSubContent.replaceAll(fieldMapJsonObj.get("CLASS_NAME_VAR").toString(),
							StringUtils.uncapitalize(name).replaceAll(" ", ""));
					tmplSubContent = tmplSubContent.replaceAll((String) fieldMapJsonObj.get("CLASS_NAME_KEBAB"),
							toKebab(StringUtils.uncapitalize(name)));

					String required = "";
					if ("Y".equalsIgnoreCase(((String) fieldsJson.get("REQUIRED")))) {
						required = "required";
					}
					tmplSubContent = tmplSubContent.replaceAll("XYfield-requiredYX", required);

//					System.out.println("******************************************");
//					System.out.println("TMPLSUBCONTENT START:" + tmplSubContent);
//					System.out.println("##########################################");

					Object[] fieldsJsonKeysAry = fieldsJson.keySet().toArray();
					for (Object fieldsJsonKey : fieldsJsonKeysAry) {
						String keyField = (String) fieldsJsonKey;
						String value = (String) fieldsJson.get(keyField);
						String valueVar = StringUtils.uncapitalize(value).replaceAll(" ", "");
						String valueKebab = toKebab(StringUtils.uncapitalize(value));
//						System.out
//								.println("==>>>FIELD_" + keyField + "==>>" + fieldMapJsonObj.get("FIELD_" + keyField));
						if (fieldMapJsonObj.get("FIELD_" + keyField) != null) {
							tmplSubContent = tmplSubContent
									.replaceAll((String) fieldMapJsonObj.get("FIELD_" + keyField), value);
//							System.out.println("FIELD_" + keyField + "==>" + fieldMapJsonObj.get("FIELD_" + keyField));
//							System.out.println("value-->" + value);
//							System.out.println("FIELD_" + keyField + "==>tmplSubContent==>" + tmplSubContent);
						}

						if (fieldMapJsonObj.get("FIELD_" + keyField + "_VAR") != null) {
							tmplSubContent = tmplSubContent
									.replaceAll((String) fieldMapJsonObj.get("FIELD_" + keyField + "_VAR"), valueVar);
						}

						if (fieldMapJsonObj.get("FIELD_" + keyField + "_KEBAB") != null) {
							tmplSubContent = tmplSubContent.replaceAll(
									(String) fieldMapJsonObj.get("FIELD_" + keyField + "_KEBAB"), valueKebab);
						}
						// System.out.println("tmpl=" + tmpl);
					}
//					for (Object fieldsJsonKey : fieldsJsonKeysAry) {
//						String keyField = (String) fieldsJsonKey;
//						if (fieldMapJsonObj.get("FIELD_" + keyField) != null) {
//							tmplSubContent = tmplSubContent
//									.replaceAll((String)fieldMapJsonObj.get("FIELD_" + keyField), "");
//						}
//					}
//					tmplSubContent = tmplSubContent
//							.replaceAll(fieldMapJsonObj.get("FIELD_" + keyField).toString(), required);

//					System.out.println("TMPLSUBCONTENT END:" + tmplSubContent);
					tmplSubContentBuildr.append(tmplSubContent);
				}

//				System.out.println("### output.tmp          ==>>" + output + ".tmp");
//				System.out.println("### tmplKey             ==>>" + tmplKey);
//				System.out.println("### tmplSubContentBuildr==>>\n" + tmplSubContentBuildr);
//				System.out.println("### contentBuildr       ==>>\n" + contentBuildr);
//				System.out.println(
//						"### has key [" + tmplKey + "] ==>>\n" + (contentBuildr.toString().indexOf(tmplKey) > -1));
				contents = contents.replaceAll(tmplKey, tmplSubContentBuildr.toString());
//				System.out.println("### contents           ==>>\n" + contents);
//				System.out.println("### output.tmp          ==>>" + output + ".tmp => DONE!");
			}

			try {
				writer.write(contents);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createFile(String name, String fileName, String output) {
//		System.out.println("createFile: " + output + ".tmp");
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(output + ".tmp"))) {
			String mainTmpl = getFileContent(fileName).toString();
			mainTmpl = mainTmpl.replaceAll("XYclass-kebabYX", toKebab(StringUtils.uncapitalize(name)));
			mainTmpl = mainTmpl.replaceAll("XYclassYX", StringUtils.uncapitalize(name));
			mainTmpl = mainTmpl.replaceAll("XYCLASSYX", name);

			writer.write(mainTmpl);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private StringBuilder getFileContent(String fileName) {
		StringBuilder build = new StringBuilder();
		try (Scanner scanner = new Scanner(Paths.get(fileName))) {
			while (scanner.hasNextLine()) {
				build.append(scanner.nextLine() + NL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return build;
	}

	private String toKebab(String string) {
		String kebab = string;
		try {
			final String regex = "(?=[A-Z][a-z])";
//        final String string = "B2BNewQuoteProcess";
			final String subst = "-";

			final Pattern pattern = Pattern.compile(regex);
			final Matcher matcher = pattern.matcher(string);

			// The substituted value will be contained in the result variable
			kebab = matcher.replaceAll(subst);
			kebab = kebab.toLowerCase();
		} catch (Exception e) {
		}
		// System.out.println("Substitution result: " + kebab);
		return kebab;
	}

	private String NL = "\n";

	public static void main(String[] args) {
		new CrudeGen2();
	}
}
