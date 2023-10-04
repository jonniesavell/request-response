# async-REST
mapping request-response to async for the web ... or is it mapping async to request-response for the web?

async is excellent for backend processes. don't block waiting for someone to do what he is good at; spend your time doing what you are good at and move your work down-stream. but the web doesn't seem to work this way. are promises a way around this? i simply don't know yet. the web seems to poll. but mapping this request-response in the front-end of a CRUD app to the async processes run in the back-end is a problem that requires a solution: how can the CRUD app in the front-end keep track of long running processes launched on its behalf?

given a resource {resource}, explicity making {resource}Request and {resource}Response persisted entities and employing correlation identifiers together solve the problem of monitoring long running processes created on behalf of the front-end. you still need a worthy mechanism to serve as task queue. in the example, i have used an executor-service. longer running processes would be better served by using a persistent distributed queue.

entities {resource}Request and {resource}Response are not duplicates of each other. entity {resource}Request contains the raw materials for the long running process. we persist this to record the fact that the long running process was created by the user of the front-end application. debate exists about how many attributes beyond the correlation identifier exist within the {resource}Request entity. at a minimum, you would want the datetime on which the long running process was initiated as well as the user who initiated it. it is to be noted that the sample application contains neither.

entity {resource}Response contains the output (or a claim check to the output) of the long running process. presumably, the operator of the front-end application has an interest in its outcome.

correlation identifiers are the absolute key to the monitoring of the long running processes. we have met them before when we attempted to construct request-reply from the primitive of one way message passing. they serve us once more to correlate the user's initiation of the long running process with its eventual output.

the sample code does not allow for the recording of exceptional conditions in the {resource}Response entity, but this is absolutely necessary. if an exceptional condition occurs during the execution of a long running process, then the front-end must learn of it and this is how that will occur.
