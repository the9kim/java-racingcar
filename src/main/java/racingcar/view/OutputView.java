package racingcar.view;

import racingcar.domain.gameresult.GameResult;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printCarNamesGuide() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public void printGameRoundGuide() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public void printResultGuide() {
        System.out.println("실행 결과");
    }

    public void printResult(GameResult gameResult) {
        List<Map<String, Integer>> results = gameResult.getResults();
        for (Map<String, Integer> roundResult : results) {
            printEachResult(roundResult);
            System.out.println();
        }
    }

    private void printEachResult(Map<String, Integer> roundResult) {
        for (String key : roundResult.keySet()) {
            String position = "-".repeat(roundResult.get(key));
            System.out.printf("%s : %s%n", key, position);
        }
    }

    public void printWinners(GameResult results) {
        String winner = String.join(", ", results.findWinners());
        System.out.printf("%s가 최종 우승했습니다.%n", winner);
    }
}
