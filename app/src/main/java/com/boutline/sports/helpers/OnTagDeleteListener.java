package com.boutline.sports.helpers;

import com.boutline.sports.models.Tag;

/**
 * listener for tag delete
 */
public interface OnTagDeleteListener {
	void onTagDeleted(Tag tag, int position);
}