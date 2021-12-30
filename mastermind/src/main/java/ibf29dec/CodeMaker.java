package ibf29dec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class CodeMaker {
	private final HashMap<String, String> code = new HashMap<>();
	private final List<String> codePegs = new ArrayList<>(
			Arrays.asList("black BK", "red RD", "blue BL", "yellow YW", "green GN", "white WH"));
	private final List<String> keypegs = new ArrayList<>(Arrays.asList("black", "white"));
	private static Random random = new Random();

	public CodeMaker() {
		for (int i = 0; i < 4; i++) {
			code.put(String.format("P%d", i), getRandom(codePegs));

		}
	}

	public HashMap<String, String> getCode() {
		return this.code;
	}

	public List<String> getCodePegs() {
		return this.codePegs;
	}

	public List<String> getKeypegs() {
		return this.keypegs;
	}

	public static String getRandom(List<String> list) {
		return list.get(random.nextInt(list.size()));
	}

	public String checkAttempt(String attempt) throws NullPointerException {
		String response = "";
		HashMap<String, String> copyCode = copy(code);
		String[] attemptPegs = attempt.split(" ");
		for (int i = 0; i < attemptPegs.length; i++) {
			String currentPeg = attemptPegs[i];
			if (copyCode.get("P%d".formatted(i)).contains(currentPeg)) {
				copyCode.remove("P%d".formatted(i));
				response += "BK ";
			} else if ((copyCode.values().stream().distinct().anyMatch(s -> s.contains(currentPeg)))) {
				List<String> removeKey = copyCode.keySet().stream().filter(s -> copyCode.get(s).contains(currentPeg))
						.toList();
				copyCode.put(removeKey.get(0), "");
				response += "WH ";
			}
		}
		return response;
	}

	public static HashMap<String, String> copy(HashMap<String, String> original) {
		HashMap<String, String> copy = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : original.entrySet()) {
			copy.put(entry.getKey(), entry.getValue());
		}
		return copy;
	}

}
