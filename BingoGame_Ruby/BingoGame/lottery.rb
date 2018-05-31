class Lottery
  def initialize
    @stored_numbers = []
    100.times do |i|
      @stored_numbers << (i + 1)
    end
  end

  # 当選番号を1個ずつ返すメソッド
  def lottery
    @stored_numbers.shuffle!
    lottery_number = @stored_numbers.at(0)
    @stored_numbers.shift
    lottery_number
  end

end