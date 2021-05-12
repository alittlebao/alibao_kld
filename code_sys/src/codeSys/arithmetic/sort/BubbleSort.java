package codeSys.arithmetic.sort;

import java.util.Arrays;

/**
 * 
 * 冒泡排序---交换类排序---非线性时间比较类排序
 *
 * <p>非线性时间比较类排序：通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)，因此称为非线性时间比较类排序。
 * <p>线性时间非比较类排序：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此称为线性时间非比较类排序。
 * 
 * 算法描述
 * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 针对所有的元素重复以上的步骤，除了最后一个；
 * 重复步骤1~3，直到排序完成。
 * @author Administrator
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2019年2月10日     Administrator           v1.0.0               修改原因
 */
public class BubbleSort {

	private static int[] normalBubbleSort(int[] array) {

		int length = array.length;

		for (int i = 0; i < length - 1; i++) {
			/*
			 * length - 1 - i
			 */
			for (int j = 0; j < length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}

		return array;

	}

	public static void main(String[] args) {

		int[] array = new int[] { 101, 77, 99, 2, 44, 55, 75, 22, 32, 34, 27, 12, 15 };

		array = normalBubbleSort(array);

		System.out.println(Arrays.toString(array));
	}

}
