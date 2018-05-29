class Lottery
  def initialize
    @storedNumbers = []
    for i in 1..100 do
      @storedNumbers << i
    end
  end

  # 当選番号を1個ずつ返すメソッド
  def lottery
    @storedNumbers.shuffle!
    lotteryNumber = @storedNumbers.at(0)
    @storedNumbers.shift
    return lotteryNumber
  end

end