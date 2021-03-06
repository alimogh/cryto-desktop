package PeriodBittrex;

import java.util.ArrayList;

public class PeriodBittrex implements Period {
	protected long idStart;
	protected long idEnd;
	protected long startTime = 0;
	protected long endTime = 0;
	protected int high = 0;
	protected long timeHigh = 0;
	protected int low = 1000000;
	protected long timeLow = 0;
	protected long open = 0;
	protected long close = 0;
	protected double average = 0;
	protected double volumn = 0;
	protected double total = 0;
	protected double volumnBuy = 0;
	protected double volumnSell = 0;
	protected boolean pump;

	protected ArrayList<MarketHistory> data = new ArrayList<>();

	// public static Date startTime;

	public void setStart(long start) {
		this.startTime = start;
	}

	protected void printData() {
		for (MarketHistory marketHistory : data) {
			System.out.println(marketHistory.toString());
		}
	}

	public void fill(MarketHistory[] data) {
		if (startTime == 0) {
			this.startTime = data[data.length - 1].timeStamp;
			this.idStart = data[data.length - 1].id;
		}
		for (MarketHistory marketHistory : data) {
			if (marketHistory.id >= this.idStart) {
				this.data.add(marketHistory);

			}
		}
		this.endTime = data[0].timeStamp;
		// printData();
		calculator();
	}

	public void calculator() {
		this.startTime = data.get(0).timeStamp;
		this.open = data.get(0).price;
		this.endTime = data.get(data.size() - 1).timeStamp;
		this.close = data.get(data.size() - 1).price;
		System.err.println("size: " + data.size());
		System.out.println("High: " + this.high + ", Low: " + this.low);
		for (MarketHistory marketHistory : data) {
			if (data.size() == 0) {
				return;
			}
			if (marketHistory.price > this.high) {
				this.high = marketHistory.price;
				this.timeHigh = marketHistory.timeStamp;
			}
			if (marketHistory.price < this.low) {
				this.low = marketHistory.price;
				this.timeLow = marketHistory.timeStamp;
			}
			if (marketHistory.type == MarketHistory.TYPE_BUY) {
				this.volumnBuy += marketHistory.quantity;
			} else if (marketHistory.type == MarketHistory.TYPE_SELL) {
				this.volumnSell += marketHistory.quantity;
			}
			this.volumn += marketHistory.quantity;
			this.total += marketHistory.total;
		}
		System.out.println("High: " + this.high + ", Low: " + this.low);

		this.average = total / volumn;

	}

	public void reset() {
		startTime = 0;
		endTime = 0;
		high = 0;
		low = 1000000;
		volumn = 0;
		volumnBuy = 0;
		volumnSell = 0;
		total = 0;
		average = 0;
		open = 0;
		close = 0;
	}

	public int getHigh() {
		return high;
	}

	public int getLow() {
		return low;
	}

	public double getAverage() {
		return low;
	}

	public double getOpen() {
		return low;
	}

	public double getClose() {
		return low;
	}

	public double getVolumn() {
		return low;
	}

	public double getTotal() {
		return low;
	}

}
