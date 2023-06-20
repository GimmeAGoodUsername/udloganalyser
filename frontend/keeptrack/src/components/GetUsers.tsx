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
                            <p>Title: <span>{post.name}</span></p>
                            <p>Body: <span>{post.race}</span></p>
                            <p>User: <span>{post.role}</span></p>
                        </div>
                    ))
                }
            </div>
        </>
    )
}