#### トップレベルのメソッド ####
# ビンゴカードのサイズが適切か確認するメソッド
require "./bingo_card.rb"
require "./lottery.rb"

# loop_with_indexを使うための設定モジュール
module Kernel
  def loop_with_index(initial_number = 0)
    count = initial_number
    loop do
      yield(count)
      count += 1
    end
  end
end

# 1辺のマス目の数が適切か確認するメソッド
def check_side_size(side_size)
  loop {
    if side_size > 0 && side_size <= 10 then
      break
    end
    puts("カードのサイズは1~10の範囲しか選べません！：")
    side_size = gets.to_i
  }
  side_size
end

# プレイヤー人数が適切か確認するメソッド
def check_player_number(player_number)
  loop {
    if player_number.to_i > 0 then
      break
    end
    puts("プレイヤー人数は1人以上です！：")
    player_number = gets.to_i
  }
  player_number
end

# 当選番号を取得・表示するメソッド
def check_lottery_number(lot, lot_count)
  lottery_number = lot.lottery
  puts("")
  puts("#{lot_count}回目：当選番号は#{lottery_number}です")
  lottery_number
end

# ビンゴした人数を返すメソッド
def check_bingo(cards, lottery_number)
  cards.each.with_index(1) do |card, i|
    puts("【Player#{i}】")
    card.judge_hit(lottery_number)
    card.show_table
    if card.bingo? then
      puts("#{card.bingo_count}ビンゴ！！！")
    end
  end
  cards.select{|card| card.bingo?}.count
end

#### ここからメイン処理 ####
puts("ビンゴカードのサイズを決めてください：")
side_size = check_side_size(gets.to_i)
puts("何人でプレイしますか？：")
player_number = check_player_number(gets.to_i)
puts("\n#{player_number}人で#{side_size}x#{side_size}マスのビンゴを始めます")

cards = Array.new
player_number.times do |i|
  cards << Bingo_card.new(side_size)
  card = cards.at(i)
  puts("【Player#{i + 1}】")
  card.show_table
end

lot = Lottery.new
loop_with_index(1) do |lot_count|
  lottery_number = check_lottery_number(lot, lot_count)
  bingo_player_count = check_bingo(cards, lottery_number)
  if bingo_player_count > 0 then
    puts("#{bingo_player_count}人ビンゴしたのでおわり")
    break
  end
end