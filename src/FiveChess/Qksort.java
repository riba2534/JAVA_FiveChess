package FiveChess;

public class Qksort {
	Lin[] temp = new Lin[225];

	public Qksort(Lin temp[]) {
		this.temp = temp;

	}

	public Lin[] getQksort() {
		QuickSort_list(0, temp.length - 1);
		return temp;
	}

	public void QuickSort_list(int low, int high) {
		int pivotpos;
		if (low < high) {
			pivotpos = Partition_list(low, high);
			QuickSort_list(low, pivotpos - 1);
			QuickSort_list(pivotpos + 1, high);
		}
	}

	public int Partition_list(int i, int j) {
		Lin pivot = new Lin();
		pivot = temp[i];
		while (i < j) {
			while (i < j && temp[j].getFraction() >= pivot.getFraction())
				j--;
			if (i < j)
				temp[i++] = temp[j];
			while (i < j && temp[i].getFraction() <= pivot.getFraction())
				i++;
			if (i < j)
				temp[j--] = temp[i];
		}
		temp[i] = pivot;
		return i;
	}
}
