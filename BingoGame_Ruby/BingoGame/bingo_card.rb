class Bingo_card
  HIT_MARKER = "○"

  def initialize(side_size)
    @side_size = side_size
  end

  # ビンゴの初期配置を決めるメソッド
  def initial_place
    @random_numbers = [*1..100].shuffle
    @card_numbers = (0..@side_size - 1).map {@random_numbers.slice!(0, @side_size)}
  end

  # 当選したかどうか判定、数字を更新するメソッド
  def judge_hit(lottery_number)
    @card_numbers.each {|column_numbers|
      hit_index = 0
      column_numbers.each {|number|
        if lottery_number.to_s == number.to_s then
          puts("#{lottery_number}が当たりました！")
          column_numbers[hit_index] = HIT_MARKER
          break
        end
        hit_index += 1
      }
    }
  end

  # ビンゴしているかどうか判定するメソッド
  def is_bingo
    @bingo_count = 0
    # 横がそろっているかチェック
    @card_numbers.each {|row_numbers|
      if row_numbers.all? {|number| number == HIT_MARKER} then
        @bingo_count += 1
      end
    }
    # 縦がそろっているかチェック
    @side_size.times do |i|
      if @card_numbers.all? {|row_numbers| row_numbers.at(i) == HIT_MARKER} then
        @bingo_count += 1
      end
    end
    # 斜め↘︎がそろっているかチェック
    if @card_numbers.all? {|row_numbers| row_numbers.at(@card_numbers.find_index(row_numbers)) == HIT_MARKER} then
      @bingo_count += 1
    end
    # 斜め↗︎がそろっているかチェック
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
