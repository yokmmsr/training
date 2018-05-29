class BingoCard
  HIT_MARKER = "○"

  def initialize(sideSize)
    @sideSize = sideSize
  end

  def getBingoCount
    return @bingoCount
  end

  # ビンゴの初期配置を決めるメソッド
  def initialPlace
    @cardNumbers = []
    @randomNumbers = []
    for i in 1..100 do
      @randomNumbers << i.to_s
    end
    @randomNumbers.shuffle!
    num = 0
    for i in 0..@sideSize - 1 do
      columnNumbers = []
      for j in 0..@sideSize - 1 do
        columnNumbers << @randomNumbers.at(num)
        num += 1
      end
      @cardNumbers << columnNumbers
    end
  end

  # 当選したかどうか判定、数字を更新するメソッド
  def judgeHit(lotteryNumber)
    @cardNumbers.each { |columnNumbers|
      hitIndex = 0
      columnNumbers.each { |number|
        if lotteryNumber.to_s == number then
          puts("#{lotteryNumber}が当たりました！")
          columnNumbers[hitIndex] = HIT_MARKER
          break
        end
        hitIndex += 1
      }
    }
  end

  # ビンゴしているかどうか判定するメソッド
  def isBingo
    @bingoCount = 0

    hitCount = 0
    @cardNumbers.each { |rowNumbers|
      hitCount = 0
      rowNumbers.each { |number|
        unless number == HIT_MARKER then
          break
        end
        hitCount += 1
      }
      if hitCount == @sideSize then
        @bingoCount += 1
      end
      hitCount = 0
    }

    for i in 0..@sideSize - 1
      @cardNumbers.each { |rowNumbers|
        unless rowNumbers.at(i) == HIT_MARKER then
          break
        end
        hitCount += 1
      }
      if hitCount == @sideSize then
        @bingoCount += 1
      end
      hitCount  = 0
    end

    for i in 0..@sideSize - 1 do
      unless @cardNumbers.at(i).at(i) == HIT_MARKER then
        break
      end
      hitCount += 1
      if hitCount == @sideSize then
        @bingoCount += 1
      end
    end
    hitCount = 0

    for i in 0..@sideSize - 1 do
      unless @cardNumbers.at(i).at(@sideSize - i -1) == HIT_MARKER then
        break
      end
      hitCount += 1
      if hitCount == @sideSize then
        @bingoCount += 1
      end
    end
    hitCount = 0

    return @bingoCount > 0

  end

  # 表形式で表示するメソッド
  def showTable
    print(" ┌")
    for i in 0..@sideSize * 4 do
      print("-")
    end
    puts("┐")
    @cardNumbers.each { |columnNumbers|
      print(" │")
      columnNumbers.each { |number|
        printf("%4s", number)
      }
      puts(" │")
    }
    print(" └")
    for i in 0..@sideSize * 4 do
      print("-")
    end
    puts("┘")
  end

end
