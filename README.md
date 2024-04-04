# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Ground Rule
* 테스트는 한국어로 작성한다.
* 테스트 메소드 명 맨 뒤에 `테스트`를 추가한다.
* 10분마다 역할을 변경한다.
* 40분마다 휴식을 취한다.

## TODO List
- [X] 사용자가 금액 입력
- [X] 1000으로 나누었을 때 몫만큼 구매한다.
- [X] LottoSystem이 구매 개수 파악
- [X] LottoSystem이 랜덤 로또 용지 생성
- [X] 결과 클래스는 로또 별 당첨 여부 확인
- [X] Ranking enum은 로또의 순위 화인
- [X] Lottos클래스는 Lotto의 리스트를 가진다.
- [X] Lotto의 숫자는 중복되지 않아야한다.
- [X] Ball은 1~45에 포함되는지 확인한다.
- [X] 랜덤을 위한 인터페이스를 개발한다.
- [X] 보너스볼을 생성하는 기능을 추가한다.
- [X] 사용자가 당첨 번호 입력
- [X] 사용자가 보너스 볼 입력
- [X] View가 일치 개수 출력
- [X] 등수에 따른 가격 계산
- [X] LottoSystem이 총 수익률 계산
- [X] View가 총 수익률 출력
- [X] 로또 번호는 오름차순이어야 한다.
- [X] 결과 클래스 생성
- [X] 정답 클래스 생성
- [X] 정답 클래스는 보너스 번호가 로또 번호와 겹치면 안된다.

## 리팩터링 리스트
- [X] Money 클래스 래핑
- [X] 순위 별 당첨금액 위치 변경
- [X] 패키지화
- [X] contains를 사용한 리팩터링

## 메모장


### Lotto를 사용할 때

재사용 향상

null접근 위험

### 새 클래스를 만들 때

compare함수를 여기에 넣는다.

---

Ball <- Lotto <- Lottos <- LottoSystem

                                |

                                V

                            RandomGenerator

List<Ball> numbers, Ball bonus

Result
