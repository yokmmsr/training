#### トップレベルのメソッド ####
# ビンゴカードのサイズが適切か確認するメソッド
require "./bingo_card.rb"
require "./lottery.rb"

def setSideSize(sideSize)
  loop {
    if sideSize.to_i > 0 && sideSize.to_i <= 10 then
      break
    end
    puts("カードのサイズは1~10の範囲しか選べません！")
  }
  puts("#{sideSize}x#{sideSize}マスのビンゴを始めます")
end

# プレイヤー人数が適切か確認するメソッド
def setPlayerNumber(playerNumber)
  loop {
    if playerNumber.to_i > 0 then
      break
    end
    puts("プレイヤー人数は1人以上です！")
  }

end

# 当選番号を取得・表示するメソッド
def getLotteryNumber(lot, lotCount)
  lotteryNumber = lot.lottery
  puts("")
  puts("#{lotCount}回目：当選番号は#{lotteryNumber}です")
  return lotteryNumber
end

# ビンゴした人数を返すメソッド
def checkBingo(cards, playerNumber, lotteryNumber)
  bingoPlayerCount = 0
  for i in 0..playerNumber - 1 do
    card = cards.at(i)
    puts("【Player#{i + 1}】")
    card.judgeHit(lotteryNumber)
    card.showTable
    if card.isBingo then
      puts("#{card.getBingoCount}ビンゴ！！！")
      bingoPlayerCount += 1
    end
  end
  return bingoPlayerCount
end


#### ここからメイン処理 ####
puts("ビンゴカードのサイズを決めてください：")
sideSize = gets.to_i
setSideSize(sideSize)
puts("何人でプレイしますか？：")
playerNumber = gets.to_i
setPlayerNumber(playerNumber)

cards = Array.new
for i in 0..playerNumber - 1 do
  cards << BingoCard.new(sideSize)
  card = cards.at(i)
  card.initialPlace
  puts("【Player#{i + 1}】")
  card.showTable
end

lot = Lottery.new
lotCount = 0
loop {
  lotCount += 1
  lotteryNumber = getLotteryNumber(lot, lotCount)
  bingoPlayerCount = checkBingo(cards, playerNumber, lotteryNumber)
  if bingoPlayerCount > 0 then
    puts("#{bingoPlayerCount}人ビンゴしたのでおわり")
    break
  end
}