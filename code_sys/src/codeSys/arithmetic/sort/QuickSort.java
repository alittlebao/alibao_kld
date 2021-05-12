package codeSys.arithmetic.sort;

import java.util.Arrays;

/**
 * 
 * 快速排序---交换类排序---非线性时间比较类排序 
 * 
 * 
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * 1 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 2 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 *
 * @author Administrator
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2019年2月10日     Administrator           v1.0.0               修改原因
 */
public class QuickSort {

	public static void main(String[] args) {

		int[] array = new int[] { 101, 77, 99, 2, 44, 55, 75, 22, 32, 34, 27, 12, 15 };

		array = normalQuickSort(array, 0, array.length - 1);

		System.out.println(Arrays.toString(array));
	}

	private static int[] normalQuickSort(int[] array, int left, int right) {

		if (left < right) {

			int index = partSort(array, left, right);
			normalQuickSort(array, left, index - 1);
			normalQuickSort(array, index + 1, right);

		}

		return array;
	}

	//单路快排
	private static int partSort(int[] array, int left, int right) {

		int basePoint = array[right];
		int i = left - 1;

		for (int j = left; j <= right; j++) {

			if (array[j] < basePoint) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, i + 1, right);

		return i + 1;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
