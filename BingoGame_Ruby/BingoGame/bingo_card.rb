class Bingo_card
  HIT_MARKER = "○"

  def initialize(side_size)
    @side_size = side_size
    # ビンゴの初期配置を決める処理
    @random_numbers = [*1..100].shuffle
    @card_numbers = (0..@side_size - 1).map {@random_numbers.slice!(0, @side_size)}
  end

  # 当選したかどうか判定、数字を更新するメソッド
  def judge_hit(lottery_number)
    @card_numbers.each {|column_numbers|
      column_numbers.each_with_index do |number, hit_index|
        if lottery_number.to_s == number.to_s then
          puts("#{lottery_number}が当たりました！")
          column_numbers[hit_index] = HIT_MARKER
          break
        end
      end
    }
  end

  # ビンゴしているかどうか判定するメソッド
  def bingo?
    @bingo_count = 0
    # 横がそろっているかチェック
    @bingo_count += @card_numbers.select {|row_numbers| row_numbers.all? {|number| number == HIT_MARKER}}.count
    # 縦がそろっているかチェック
    @bingo_count += (0..@side_size - 1).select {|i| @card_numbers.all? {|row_numbers| row_numbers.at(i) == HIT_MARKER}}.count
    # 斜め↘︎がそろっているかチェック
    if @card_numbers.all? {|row_numbers| row_numbers.at(@card_numbers.find_index(row_numbers)) == HIT_MARKER} then
      @bingo_count += 1
    end
    # 斜め↙︎がそろっているかチェック
    if @card_numbers.all? {|row_numbers| row_numbers.at(@side_size - (@card_numbers.find_index(row_numbers)) - 1) == HIT_MARKER} then
      @bingo_count += 1
    end

    @bingo_count > 0
  end

  # 表形式で表示するメソッド
  def show_table
    print(" ┌")
    (@side_size * 2).times {print(" -")}
    puts(" ┐")
    @card_numbers.each {|column_numbers|
      print(" │")
      column_numbers.each {|number|
        printf("%4s", number)
      }
      puts(" │")
    }
    print(" └")
    (@side_size * 2).times {print(" -")}
    puts(" ┘")
  end

  attr_accessor :bingo_count

end
