import { useEffect, useState } from 'react';
import { getAllUsers } from '../util/getData';
import { ResponseAPI } from '../api/client';

export const GetUsers = () => {

    const [posts, setPosts] = useState<ResponseAPI[]>([])
    useEffect(() => {
        // You can implement a <Loading/>
        //  start loading
        getAllUsers().then(data => setPosts(data))
        //  finish loading
    }, [])
    return (
        <>
            <h1>Get Post 👇</h1><br />
            <h2>posts list</h2>

            <div className='grid'>
                {
                    posts.map(post => (
                        <div key={post.id}>
                            <p>User: <span>{post.name}</span></p>
                            <p>Race: <span>{post.race}</span></p>
                            <p>Role: <span>{post.role}</span></p>
                            <p>id: <span>{post.id}</span></p>

                        </div>
                    ))
                }
            </div>
        </>
    )
}