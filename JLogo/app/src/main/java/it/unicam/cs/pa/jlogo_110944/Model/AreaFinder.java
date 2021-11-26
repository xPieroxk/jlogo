package it.unicam.cs.pa.jlogo_110944.Model;

import java.util.Optional;

/**
 * Search closed areas
 */
public interface AreaFinder<L> {

    /**
     * Determines if the lines contained in the panel associated with this
     * area finder form a closed area.
     *
     * @return a closed area if one has been identified, otherwise Optional.empty() is returned
     */
    Optional<Area<L>> findClosedArea();
}
