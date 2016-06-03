package com.arangodb.velocypack.util;

import java.math.BigInteger;
import java.util.Collection;

/**
 * @author Mark - mark@arangodb.com
 *
 */
public class NumberUtil {

	public static double toDouble(final byte[] array, final int offset, final int length) {
		return Double.longBitsToDouble(toLong(array, offset, Double.BYTES));
	}

	public static byte[] toByteArray(final double value) {
		return toByteArray(Double.doubleToRawLongBits(value), Double.BYTES);
	}

	public static void append(final Collection<Byte> buffer, final double value) {
		append(buffer, Double.doubleToRawLongBits(value), Double.BYTES);
	}

	public static long toLong(final byte[] array, final int offset, final int length) {
		long result = 0;
		for (int i = offset; i < (offset + length); i++) {
			result <<= 8;
			result |= (array[i] & 0xFF);
		}
		return result;
	}

	public static long toLongReversed(final byte[] array, final int offset, final int length) {
		long result = 0;
		for (int i = (offset + length - 1); i >= offset; i--) {
			result <<= 8;
			result |= (array[i] & 0xFF);
		}
		return result;
	}

	public static byte[] toByteArray(final long value, final int length) {
		long l = value;
		final byte[] result = new byte[length];
		for (int i = (length - 1); i >= 0; i--) {
			result[i] = (byte) (l & 0xFF);
			l >>= 8;
		}
		return result;
	}

	public static void append(final Collection<Byte> buffer, final long value, final int length) {
		final long l = value;
		for (int i = 0; i < length; i++) {
			buffer.add((byte) (l >> (length - i - 1 << 3)));
		}
	}

	public static void appendReversed(final Collection<Byte> buffer, final long value, final int length) {
		final long l = value;
		for (int i = length - 1; i >= 0; i--) {
			buffer.add((byte) (l >> (length - i - 1 << 3)));
		}
	}

	public static BigInteger toBigInteger(final byte[] array, final int offset, final int length) {
		BigInteger result = new BigInteger(1, new byte[] {});
		for (int i = offset; i < (offset + length); i++) {
			result = result.shiftLeft(8);
			result = result.or(BigInteger.valueOf(array[i] & 0xFF));
		}
		return result;
	}

	public static byte[] toByteArray(final BigInteger value, final int length) {
		BigInteger big = value;
		final byte[] result = new byte[length];
		for (int i = (length - 1); i >= 0; i--) {
			result[i] = (byte) (big.byteValue() & 0xFF);
			big = big.shiftRight(8);
		}
		return result;
	}

	public static void append(final Collection<Byte> buffer, final BigInteger value, final int length) {
		final BigInteger l = value;
		for (int i = 0; i < length; ++i) {
			buffer.add(l.shiftRight(length - i - 1 << 3).byteValue());
		}
	}

	/**
	 * read a variable length integer in unsigned LEB128 format
	 */
	public static long readVariableValueLength(final byte[] array, final int offset, final boolean reverse) {
		long len = 0;
		long v;
		long p = 0;
		int i = offset;
		do {
			v = array[i];
			len += (v & 0x7f) << p;
			p += 7;
			if (reverse) {
				--i;
			} else {
				++i;
			}
		} while (v >= 0x80);
		return len;
	}

	/**
	 * calculate the length of a variable length integer in unsigned LEB128
	 * format
	 */
	public static long getVariableValueLength(final long value) {
		long len = 1;
		long val = value;
		while (val >= 0x80) {
			val >>= 7;
			++len;
		}
		return len;
	}
}
