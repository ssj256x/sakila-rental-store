package com.backend.sakila.mappers;

import com.backend.sakila.api.model.Actor;
import com.backend.sakila.model.entity.ActorEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ActorAndActorEntityMapper {

    /**
     * Map ActorEntity to Actor.
     *
     * @param actorEntity - ActorEntity to be mapped.
     * @return Actor - mapped Actor.
     */
    ActorEntity actortoActorEntity(ActorEntity actorEntity);

    /**
     * Map Actor to ActorEntity.
     *
     * @param actorEntity - Actor to be mapped.
     * @return ActorEntity - mapped ActorEntity.
     */
    Actor actorEntitytoActor(ActorEntity actorEntity);

}
