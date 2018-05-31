class Lottery
  def initialize
    @stored_numbers = [*1..100]
  end

  # 当選番号を1個ずつ返すメソッド
  def lottery
    lottery_number = @stored_numbers.shuffle!.shift
  end

end